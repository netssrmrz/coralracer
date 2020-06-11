import {LitElement, html, css} from "./lit-element/lit-element.js";

// Editor Def ======================================================================================

class Canvas_Editor extends LitElement
{
  constructor()
  {
    super();
    this.paint_style = "stroke";
    this.shapes = null;
    this.OnMouseMove_Canvas = this.OnMouseMove_Canvas.bind(this);
    this.OnMouseDown_Canvas = this.OnMouseDown_Canvas.bind(this);
    this.OnMouseUp_Canvas = this.OnMouseUp_Canvas.bind(this);
    this.Render = this.Render.bind(this);
  }
  
  firstUpdated(changedProperties)
  {
    this.canvas = this.shadowRoot.getElementById("main_canvas");
    this.ctx = this.canvas.getContext("2d");
    this.Init_Canvas(1, 1000, 1000);
    this.Enable_Events();
    this.Set_Zoom(1);
    this.Set_Paint("stroke");
  }

  Init_Canvas(zoom, width, height)
  {
    this.canvas.width = width;
    this.canvas.height = height;        
    this.ctx.x_scale = zoom;
    this.ctx.y_scale = zoom;

    this.ctx.globalCompositeOperation = "difference";
    this.ctx.setTransform(1, 0, 0, 1, 0, 0);
    this.ctx.translate(this.canvas.width/2, this.canvas.height/2);
    this.ctx.scale(zoom, -zoom);
    this.ctx.strokeStyle="#fff";
    this.ctx.fillStyle="#fff";
    this.ctx.lineWidth = 1;
  }

  Disable_Events()
  {
    this.canvas.removeEventListener('mousemove', this.OnMouseMove_Canvas);
    this.canvas.removeEventListener('mousedown', this.OnMouseDown_Canvas);
    this.canvas.removeEventListener('mouseup', this.OnMouseUp_Canvas);
  }

  Enable_Events()
  {
    this.canvas.addEventListener('mousemove', this.OnMouseMove_Canvas);
    this.canvas.addEventListener('mousedown', this.OnMouseDown_Canvas);
    this.canvas.addEventListener('mouseup', this.OnMouseUp_Canvas);
  }

  Set_Shapes(shapes)
  {
    this.shapes = shapes;
    this.Render(this.ctx, shapes);
  }

  Set_Zoom(zoom)
  {
    const large_btn = this.shadowRoot.getElementById("large_btn");
    const medium_btn = this.shadowRoot.getElementById("medium_btn");
    const small_btn = this.shadowRoot.getElementById("small_btn");
    large_btn.classList.remove("selected");
    medium_btn.classList.remove("selected");
    small_btn.classList.remove("selected");
    if (zoom==1) large_btn.classList.add("selected");
    else if (zoom==3) medium_btn.classList.add("selected");
    else if (zoom==10) small_btn.classList.add("selected");

    this.Init_Canvas(zoom, this.canvas.width, this.canvas.height);
    this.Render(this.ctx, this.shapes);
  }

  Set_Paint(paint_style)
  {
    const stroke = this.shadowRoot.getElementById("stroke");
    const fill = this.shadowRoot.getElementById("fill");
    stroke.classList.remove("selected");
    fill.classList.remove("selected");
    if (paint_style=="stroke") stroke.classList.add("selected");
    else if (paint_style=="fill") fill.classList.add("selected");

    this.paint_style = paint_style;
    this.Render(this.ctx, this.shapes);
  }

  Resize(width, height)
  {
    this.style.width = width + "px";
    this.style.height = height + "px";
    //this.Init_Canvas(this.ctx.x_scale, width-40, height-40);
    this.Init_Canvas(this.ctx.x_scale, width, height);
    this.Render(this.ctx, this.shapes);
  }

  Update()
  {
    this.Render(this.ctx, this.shapes);
  }

  // Events =======================================================================================

  OnMouseMove_Canvas(event)
  {
    let shape;

    if (this.shapes && this.shapes.length>0)
    {
      for (let i=0; i<this.shapes.length; i++)
      {
        shape = this.shapes[i];
        if (shape.On_Mouse_Move)
        {
          shape.On_Mouse_Move(event, this.ctx);
        }
      }
      this.Update();
    }
  }

  OnMouseDown_Canvas(event)
  {
    let shape;

    if (this.shapes && this.shapes.length>0)
    {
      for (let i=0; i<this.shapes.length; i++)
      {
        shape = this.shapes[i];
        if (shape.On_Mouse_Down)
        {
          shape.On_Mouse_Down(event, this.ctx);
        }
      }
      this.Update();
    }
  }

  OnMouseUp_Canvas(event)
  {
    let shape, has_change;

    if (this.shapes && this.shapes.length>0)
    {
      for (let i=0; i<this.shapes.length; i++)
      {
        shape = this.shapes[i];
        if (shape.On_Mouse_Up)
        {
          has_change = shape.On_Mouse_Up(event, this.ctx);
          if (has_change && this.on_change_fn)
          {
            this.on_change_fn(shape);
          }
        }
        this.Update();
      }
    }
  }

  OnClick_Stroke(event)
  {
    this.Set_Paint("stroke");
  }

  OnClick_Fill(event)
  {
    this.Set_Paint("fill");
  }

  OnClick_Large_Canvas(event)
  {
    this.Set_Zoom(1);
  }

  OnClick_Medium_Canvas(event)
  {
    this.Set_Zoom(3);
  }

  OnClick_Small_Canvas(event)
  {
    this.Set_Zoom(10);
  }

  OnClick_Show_Remote()
  {
    //remote_ctrl.Show();
  }

  // Gfx ==========================================================================================
  
  Clr(ctx)
  {
    ctx.clearRect(-ctx.canvas.width/2, -ctx.canvas.height/2, ctx.canvas.width, ctx.canvas.height);
  }

  Render(ctx, shapes)
  {
  }

  Render_Origin(ctx)
  {
    ctx.save();
    ctx.strokeStyle = "#111";
    ctx.lineWidth = 1;
    //ctx.setLineDash([5, 5]);

    const rx = this.canvas.width/2;
    const ry = this.canvas.height/2;
    ctx.beginPath();
    ctx.moveTo(-rx, 0);
    ctx.lineTo(rx, 0);
    ctx.moveTo(0, -ry);
    ctx.lineTo(0, ry);
    ctx.stroke();

    //ctx.setLineDash([]);
    ctx.restore();
  }

  static get styles()
  {
    return css`
      :host
      {
        display: inline-block;
        xborder: 1px solid #f00;
        overflow: hidden;
        position: absolute;
        left: 0px;
        bottom: 0px;
        z-index: 0;
      }
      #btn_bar
      {
        position: absolute;
        top: 0px;
        right: 0px;
        padding: 10px;
        display: inline-block;
        z-index: 1;
      }
      button
      {
        border-radius: 7px;
        border: 1px solid #0f0;
        padding: 5px;
        cursor: pointer;
        width: 36px;
        height: 36px;
        background-color: #666;
      }
      button img
      {
        padding: 0;
        margin: 0;
        width: 24px;
        height: 24px;
        xbackground-color: #666;
      }
      button:disabled
      {
        opacity: 0.25;
      }
      .selected
      {
        background-color: #0f0;
      }
      canvas
      {
        border: 0;
        margin: 0;
        padding: 0;   
      }
    `;
  }

  render()
  {
    return html`
      <div id="btn_bar">
        <button id="remote_btn" @click="${this.OnClick_Show_Remote}" title="Show Remote Control"><img src="images/remote.svg"></button>

        &#183; <button id="large_btn" @click="${this.OnClick_Large_Canvas}" title="Zoom x1"><img src="images/image-size-select-actual.svg"></button>
        <button id="medium_btn" @click="${this.OnClick_Medium_Canvas}" title="Zoom x3"><img src="images/image-size-select-large.svg"></button>
        <button id="small_btn" @click="${this.OnClick_Small_Canvas}" title="Zoom x10"><img src="images/image-size-select-small.svg"></button>

        &#183; <button id="stroke" @click="${this.OnClick_Stroke}" title="Stroke"><img src="images/pentagon-outline.svg"></button>
        <button id="fill" @click="${this.OnClick_Fill}" title="Fill"><img src="images/pentagon.svg"></button>
      </div>

      <canvas id="main_canvas" width="1000" height="1000"></canvas><div id="debug"></div>
    `;
  }
}

customElements.define('canvas-editor', Canvas_Editor);
export default Canvas_Editor;