﻿// Game Objects ===================================================================================

export class Game_Object
{
  constructor()
  {
    this.class_name = "Game_Object";
    this.name = "Game Object";
    this.pt = { x: 0, y: 0 };
    this.scale = { x: 1, y: 1 };
    this.angle = 0;
    this.selected = false;
    this.btns = [];
    this.New_Btn_Path("pos_btn", 0, 0);
    this.New_Btn_Path("scl_btn", 100, 100);
    this.New_Btn_Path("rot_btn", 0, 100);
    this.cmd = null;
    this.design_r = 100;
  }

  New_Btn_Path(id, x, y)
  {
    const r = 5;
    const p = new Path2D();
    p.moveTo(-r, -r);
    p.lineTo(-r, r);
    p.lineTo(r, r);
    p.lineTo(r, -r);
    p.closePath();
    p.hover = false;
    p.id = id;
    p.x = x;
    p.y = y;
    p.colour_hover = "#f0f";
    p.colour = "#0f0";
    this.btns.push(p);
    this[p.id] = p;

    return p;
  }

  Get_Pos()
  {
    return this.pt;
  }

  Set_Pos(pt)
  {
    this.pt = pt;
  }

  Params_Str()
  {
    let res = "";

    if (this.pt)
    {
      res = 
        "x = "+Round(this.pt.x)+", y = "+Round(this.pt.y) + ", " +
        "x scale = "+Round(this.scale.x)+", y scale = "+Round(this.scale.y) + ", " +
        "angle = "+Round(this.angle);
    }

    return res;
  }

  // Events =======================================================================================

  On_Mouse_Move(event, ctx)
  {
    let res = false;

    if (this.cmd)
    {
      const c_pt = this.To_Canvas_Pt(ctx, event.offsetX, event.offsetY);
      if (this.cmd.id == "pos_btn")
      {
        this.pt.x = c_pt.x;
        this.pt.y = c_pt.y;
      }
      if (this.cmd.id == "scl_btn")
      {
        const m = new DOMMatrix();
        m.translateSelf(this.pt.x, this.pt.y);
        m.rotateSelf(this.To_Degrees(this.angle));
        m.invertSelf();
        const p = new DOMPoint(c_pt.x, c_pt.y);
        const tp = p.matrixTransform(m);
        this.scale.x = tp.x / this.design_r;
        this.scale.y = tp.y / this.design_r;
      }
      if (this.cmd.id == "rot_btn")
      {
        const x_sign = Math.sign(this.scale.x);
        const y_sign = Math.sign(this.scale.y);
        const m = new DOMMatrix();
        m.translateSelf(this.pt.x, this.pt.y);
        m.scaleSelf(x_sign, y_sign);
        m.invertSelf();
        const p = new DOMPoint(c_pt.x, c_pt.y);
        const tp = p.matrixTransform(m);
        this.angle = Math.atan2(tp.y, tp.x) - (Math.PI/2);
        this.angle = this.angle * (x_sign*y_sign);
      }
    }
    else if (this.selected)
    {
      for (let i=0; i<this.btns.length; i++)
      {
        res = res || this.On_Mouse_Move_Btn(ctx, event, this.btns[i]);
      }
    }

    return res;
  }

  To_Degrees(r)
  {
    return r*(180/Math.PI);
  }

  On_Mouse_Move_Btn(ctx, event, path)
  {
    let res = false;

    ctx.save();
    ctx.translate(this.pt.x, this.pt.y);
    ctx.rotate(this.angle);
    ctx.scale(this.scale.x, this.scale.y);

    ctx.translate(path.x, path.y);
    ctx.scale(1/this.scale.x, 1/this.scale.y);

    // undo ctx scale
    ctx.scale(1/ctx.scl.x, 1/ctx.scl.y);

    const is_in_path = ctx.isPointInPath(path, event.offsetX, event.offsetY);
    if (path.hover != is_in_path)
    {
      path.hover = is_in_path;
      res = true;
    }
    ctx.restore();

    return res;
  }

  On_Mouse_Down(event, ctx)
  {
    let res = false;

    if (this.selected)
    {
      for (let i=0; i<this.btns.length; i++)
      {
        if (this.btns[i].hover)
        {
          this.cmd = this.btns[i];
          res = true;
          break;
        }
      }
    }

    return res;
  }

  On_Mouse_Move_Cmd(ctx, c_pt, cmd)
  {
    cmd.x = c_pt.x*(1/ctx.scl.x);
    cmd.y = c_pt.y*(1/ctx.scl.y);
  }

  To_Canvas_Pt(ctx, sx, sy)
  {
    const pt = {x: sx-ctx.trn.x, y: sy-ctx.trn.y};
    pt.x = pt.x/ctx.scl.x;
    pt.y = pt.y/ctx.scl.y;

    return pt;
  }

  On_Mouse_Up(event, ctx)
  {
    let res = false;

    if (this.cmd)
    {
      this.cmd = null;
      res = true;
    }

    return res;
  }

  // Rendering ====================================================================================

  Render_Design(ctx)
  {
    if (this.selected)
    {
      ctx.strokeStyle = "#fff";

      ctx.beginPath();
      ctx.rect(-this.design_r, -this.design_r, this.design_r*2, this.design_r*2);
      ctx.moveTo(0, 0);
      ctx.lineTo(0, this.design_r*2);
      ctx.stroke();

      this.Render_Btn(ctx, this.pos_btn);
      this.Render_Btn(ctx, this.scl_btn);
      this.Render_Btn(ctx, this.rot_btn);
    }
  }

  Render_Btn(ctx, path)
  {
    ctx.save();
    ctx.translate(path.x, path.y);
    ctx.scale(1/this.scale.x, 1/this.scale.y);

    // undo ctx scale
    ctx.scale(1/ctx.scl.x, 1/ctx.scl.y);

    if (path.hover)
    {
      ctx.fillStyle = path.colour_hover;
    }
    else
    {
      ctx.fillStyle = path.colour;
    }
    ctx.fill(path);

    ctx.restore();
  }

  Render(ctx)
  {
  }
}

export class Player
extends Game_Object
{
  constructor()
  {
    super();
    this.name = "Player";
  }
}

export class Finish
extends Game_Object
{
  constructor()
  {
    super();
    this.name = "Finish";
  }
}

export class Wall
extends Game_Object
{
  constructor()
  {
    super();
    this.name = "Wall";
    this.class_name = "Wall";
  }

  Render(ctx)
  {
    ctx.beginPath();
    ctx.strokeStyle = "#666";
    ctx.rect(-100, -100, 200, 200);
    ctx.stroke();
  }
}

// Shapes =========================================================================================

export class Shape
{
  constructor()
  {
    this.Init_Shape();
    this.pt = this.New_Btn_Path("pt", 0, 0);
  }

  Init_Shape()
  {
    this.class_name = "Shape";
    this.name = "shape";
    this.selected = false;
    this.cmd = null;
    this.btns = [];
    this.prev_shape = null;
    this.pt = null;
  }

  New_Btn_Path(id, x, y)
  {
    const r = 5;
    const p = new Path2D();
    p.moveTo(-r, -r);
    p.lineTo(-r, r);
    p.lineTo(r, r);
    p.lineTo(r, -r);
    p.closePath();
    p.hover = false;
    p.id = id;
    p.x = x;
    p.y = y;
    this.btns.push(p);

    return p;
  }

  To_Canvas_Pt(ctx, sx, sy)
  {
    return {x: sx-ctx.canvas.width/2-4, y: -(sy-ctx.canvas.height/2-4)};
  }

  Pt_Translate(pt, ptd)
  {
    pt.x += ptd.x;
    pt.y += ptd.y;
  }

  Pt_Scale(pt, pts)
  {
    pt.x = pt.x * pts.x;
    pt.y = pt.y * pts.y;
  }

  Pt_Difference(pta, ptb)
  {
    const x = pta.x-ptb.x;
    const y = pta.y-ptb.y;
    return {x, y};
  }

  Params_Str()
  {
    let res = "";

    if (this.pt)
    {
      res = "x = "+Round(this.pt.x)+", y = "+Round(this.pt.y);
    }

    return res;
  }

  To_Cmd_Str()
  {
    let params = "";
    const s = ", ";
    const x = this.pt.x;
    const y = this.pt.y;

    params = Append_Str(params, x, s);
    params = Append_Str(params, y, s);

    return "moveTo("+params+")";
  }

  On_Mouse_Up(event, ctx)
  {
    let res = false;

    if (this.cmd)
    {
      this.cmd = null;
      res = true;
    }

    return res;
  }

  On_Mouse_Move(event, ctx)
  {
    let res = false;

    if (this.cmd)
    {
      const c_pt = this.To_Canvas_Pt(ctx, event.offsetX, event.offsetY);
      this.On_Mouse_Move_Cmd(ctx, c_pt, this.cmd);
    }
    else if (this.selected)
    {
      for (let i=0; i<this.btns.length; i++)
      {
        res = res || this.On_Mouse_Move_Btn(ctx, event, this.btns[i]);
      }
    }

    return res;
  }

  On_Mouse_Move_Cmd(ctx, c_pt, cmd)
  {
    cmd.x = c_pt.x*(1/ctx.scl.x);
    cmd.y = c_pt.y*(1/ctx.scl.y);
  }

  On_Mouse_Move_Btn(ctx, event, path)
  {
    let res = false;

    ctx.save();
    ctx.translate(path.x, path.y);
    const is_in_path = ctx.isPointInPath(path, event.offsetX, event.offsetY);
    if (path.hover != is_in_path)
    {
      path.hover = is_in_path;
      res = true;
    }
    ctx.restore();

    return res;
  }

  On_Mouse_Down(event, ctx)
  {
    if (this.selected)
    {
      for (let i=0; i<this.btns.length; i++)
      {
        if (this.btns[i].hover)
        {
          this.cmd = this.btns[i];
          break;
        }
      }
    }

    return false;
  }

  Render(ctx)
  {
    if (this.selected)
    {
      for (let i=0; i<this.btns.length; i++)
      {
        this.Render_Btn(ctx, this.btns[i]);
      }
    }
  }

  Render_Btn(ctx, path)
  {
    const r = 5;

    ctx.save();
    ctx.translate(path.x, path.y);

    if (path.hover)
    {
      ctx.fillStyle = "#0f0";
    }
    else
    {
      ctx.fillStyle = "deeppink";
    }
    ctx.fill(path);

    ctx.restore();
  }

  Get_Pos()
  {
    let pos;

    if (this.btns)
    {
      pos = 
      {
        x: this.btns[0].x,
        y: this.btns[0].y
      };
    }

    return pos;
  }

  Set_Pos(new_pos)
  {
    if (this.btns)
    {
      if (this.btns.length>1)
      {
        const o_pos = this.Get_Pos();
        for (let i=1; i<this.btns.length; i++)
        {
          const curr_pt = this.btns[i];
          const d_pt = this.Pt_Difference(curr_pt, o_pos);

          curr_pt.x = new_pos.x;
          curr_pt.y = new_pos.y;
          this.Pt_Translate(curr_pt, d_pt);
        }
      }
      this.btns[0].x = new_pos.x;
      this.btns[0].y = new_pos.y;
    }
  }
}

export class Shape_Arc extends Shape
{
  constructor()
  {
    super();
    this.cp = this.New_Btn_Path("cp", 100, 100);
    this.sa = this.New_Btn_Path("sa", 100, 0);
    this.ea = this.New_Btn_Path("ea", -120, 0);
  }

  Params_Str()
  {
    const s = ", ";
    let res = "";

    res = Append_Str(res, "x = "+Round(this.pt.x), s);
    res = Append_Str(res, "y = "+Round(this.pt.y), s);
    res = Append_Str(res, "radius = "+Round(this.Calc_Radius()), s);
    res = Append_Str(res, "<br>startAngle = "+Round(this.Calc_Start_Angle()), s);
    res = Append_Str(res, "endAngle = "+Round(this.Calc_End_Angle()), s);

    return res;
  }

  Calc_Radius()
  {
    let r = 0;
    const ptc = this.Pt_Difference(this.cp, this.pt);
    //const r = Math.hypot(ptc.x, ptc.y);
    if (Math.abs(ptc.x)<Math.abs(ptc.y))
    {
      r = Math.abs(ptc.x);
    }
    else
    {
      r = Math.abs(ptc.y);
    }

    return r;
  }

  Calc_Start_Angle()
  {
    const pta = this.Pt_Difference(this.sa, this.pt);
    return Math.atan2(pta.y, pta.x);
  }

  Calc_End_Angle()
  {
    const ptb = this.Pt_Difference(this.ea, this.pt);
    return Math.atan2(ptb.y, ptb.x);
  }

  To_Cmd_Str()
  {
    const s = ", ";
    const x = this.pt.x;
    const y = this.pt.y;
    const radius = this.Calc_Radius();
    const startAngle = this.Calc_Start_Angle();
    const endAngle = this.Calc_End_Angle();
    let params;

    params = Append_Str(params, x, s);
    params = Append_Str(params, y, s);
    params = Append_Str(params, radius, s);
    params = Append_Str(params, startAngle, s);
    params = Append_Str(params, endAngle, s);

    return "arc("+params+")";
  }

  On_Mouse_Move_Cmd(ctx, c_pt, cmd)
  {
    if (cmd.id == "pt")
    {
      const pts = {x: 1/ctx.scl.x, y: 1/ctx.scl.y};
      const c_pt_s = {x: c_pt.x, y: c_pt.y};
      this.Pt_Scale(c_pt_s, pts);
      const ptd = this.Pt_Difference(c_pt_s, cmd);

      this.Pt_Translate(this.cp, ptd);
      this.Pt_Translate(this.sa, ptd);
      this.Pt_Translate(this.ea, ptd);
    }
    super.On_Mouse_Move_Cmd(ctx, c_pt, cmd);
  }

  Render(ctx)
  {
    let r;

    super.Render(ctx);
    ctx.arc(this.pt.x, this.pt.y, this.Calc_Radius(), 
      this.Calc_Start_Angle(), this.Calc_End_Angle()); // [, anticlockwise]);
  }

  Render_Design(ctx)
  {
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = "#aaa";
    ctx.setLineDash([5, 5]);

    const cp2 = {x: 2*this.pt.x-this.cp.x, y: 2*this.pt.y-this.cp.y};
    ctx.moveTo(this.cp.x, this.cp.y);
    ctx.lineTo(this.cp.x, cp2.y);
    ctx.lineTo(cp2.x, cp2.y);
    ctx.lineTo(cp2.x, this.cp.y);
    ctx.lineTo(this.cp.x, this.cp.y);

    ctx.moveTo(this.pt.x, this.pt.y);
    ctx.lineTo(this.sa.x, this.sa.y);

    ctx.moveTo(this.pt.x, this.pt.y);
    ctx.lineTo(this.ea.x, this.ea.y);

    ctx.stroke();
    ctx.restore();
  }
}

export class Shape_Ellipse extends Shape
{
  constructor()
  {
    super();
    this.cp = this.New_Btn_Path("cp", 100, 100);
    this.sa = this.New_Btn_Path("sa", 100, 0);
    this.ea = this.New_Btn_Path("ea", -120, 0);
  }

  Params_Str()
  {
    const s = ", ";
    let res = "";

    res = Append_Str(res, "x = "+Round(this.pt.x), s);
    res = Append_Str(res, "y = "+Round(this.pt.y), s);
    res = Append_Str(res, "radiusx = "+Round(this.Calc_Radius_X()), s);
    res = Append_Str(res, "radiusy = "+Round(this.Calc_Radius_Y()), s);
    res = Append_Str(res, "<br>startAngle = "+Round(this.Calc_Start_Angle()), s);
    res = Append_Str(res, "endAngle = "+Round(this.Calc_End_Angle()), s);

    return res;
  }

  To_Cmd_Str()
  {
    const s = ", ";
    const x = this.pt.x;
    const y = this.pt.y;
    const radius_x = this.Calc_Radius_X();
    const radius_y = this.Calc_Radius_Y();
    const rotation = 0;
    const startAngle = this.Calc_Start_Angle();
    const endAngle = this.Calc_End_Angle();
    let params;

    params = Append_Str(params, x, s);
    params = Append_Str(params, y, s);
    params = Append_Str(params, radius_x, s);
    params = Append_Str(params, radius_y, s);
    params = Append_Str(params, rotation, s);
    params = Append_Str(params, startAngle, s);
    params = Append_Str(params, endAngle, s);

    return "ellipse("+params+")";
  }

  Calc_Radius_X()
  {
    return Math.abs(this.cp.x-this.pt.x);
  }

  Calc_Radius_Y()
  {
    return Math.abs(this.cp.y-this.pt.y);
  }

  Calc_Start_Angle()
  {
    const pta = this.Pt_Difference(this.sa, this.pt);
    return Math.atan2(pta.y, pta.x);
  }

  Calc_End_Angle()
  {
    const ptb = this.Pt_Difference(this.ea, this.pt);
    return Math.atan2(ptb.y, ptb.x);
  }

  On_Mouse_Move_Cmd(ctx, c_pt, cmd)
  {
    if (cmd.id == "pt")
    {
      const pts = {x: 1/ctx.scl.x, y: 1/ctx.scl.y};
      const c_pt_s = {x: c_pt.x, y: c_pt.y};
      this.Pt_Scale(c_pt_s, pts);
      const ptd = this.Pt_Difference(c_pt_s, cmd);
      
      this.Pt_Translate(this.cp, ptd);
      this.Pt_Translate(this.sa, ptd);
      this.Pt_Translate(this.ea, ptd);
    }
    super.On_Mouse_Move_Cmd(ctx, c_pt, cmd);
  }

  Render(ctx)
  {
    super.Render(ctx);
    ctx.ellipse(this.pt.x, this.pt.y, 
      this.Calc_Radius_X(), this.Calc_Radius_Y(), 
      0, this.Calc_Start_Angle(), this.Calc_End_Angle()); // [, anticlockwise]);
  }

  Render_Design(ctx)
  {
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = "#aaa";
    ctx.setLineDash([5, 5]);

    const cp2 = {x: 2*this.pt.x-this.cp.x, y: 2*this.pt.y-this.cp.y};
    ctx.moveTo(this.cp.x, this.cp.y);
    ctx.lineTo(this.cp.x, cp2.y);
    ctx.lineTo(cp2.x, cp2.y);
    ctx.lineTo(cp2.x, this.cp.y);
    ctx.lineTo(this.cp.x, this.cp.y);

    ctx.moveTo(this.pt.x, this.pt.y);
    ctx.lineTo(this.sa.x, this.sa.y);

    ctx.moveTo(this.pt.x, this.pt.y);
    ctx.lineTo(this.ea.x, this.ea.y);

    ctx.stroke();
    ctx.restore();
  }
}

export class Shape_Rect extends Shape
{
  constructor()
  {
    super();
    this.cp = this.New_Btn_Path("cp", 100, 100);
  }

  Params_Str()
  {
    const s = ", ";
    let res = "";

    res = Append_Str(res, "x = "+Round(this.pt.x), s);
    res = Append_Str(res, "y = "+Round(this.pt.y), s);
    res = Append_Str(res, "width = "+Round(this.Calc_Width()), s);
    res = Append_Str(res, "height = "+Round(this.Calc_Height()), s);

    return res;
  }

  To_Cmd_Str()
  {
    const s = ", ";
    let params;

    params = Append_Str(params, this.pt.x, s);
    params = Append_Str(params, this.pt.y, s);
    params = Append_Str(params, this.Calc_Width(), s);
    params = Append_Str(params, this.Calc_Height(), s);

    return "rect("+params+")";
  }

  Calc_Width()
  {
    return this.cp.x-this.pt.x;
  }

  Calc_Height()
  {
    return this.cp.y-this.pt.y;
  }

  Render(ctx)
  {
    super.Render(ctx);
    ctx.rect(this.pt.x, this.pt.y, this.Calc_Width(), this.Calc_Height());
  }
}

export class Shape_ClosePath extends Shape
{
  constructor()
  {
    super();
    this.Init_Shape();
  }

  Params_Str()
  {
    return "";
  }

  To_Cmd_Str()
  {
    return "closePath()";
  }

  Render(ctx)
  {
    ctx.closePath();
  }
}

export class Shape_ArcTo extends Shape
{
  constructor()
  {
    super();
    this.cp = this.New_Btn_Path("cp", 100, 100);
    this.rp = this.New_Btn_Path("rp", 100, 0);
  }

  Params_Str()
  {
    const s = ", ";
    let res = "";

    res = Append_Str(res, "x1 = "+Round(this.pt.x), s);
    res = Append_Str(res, "y1 = "+Round(this.pt.y), s);
    res = Append_Str(res, "x2 = "+Round(this.cp.x), s);
    res = Append_Str(res, "y2 = "+Round(this.cp.y), s);
    res = Append_Str(res, "r = "+Round(this.Calc_Radius()), s);

    return res;
  }

  To_Cmd_Str()
  {
    const s = ", ";
    let params;

    params = Append_Str(params, this.pt.x, s);
    params = Append_Str(params, this.pt.y, s);
    params = Append_Str(params, this.cp.x, s);
    params = Append_Str(params, this.cp.y, s);
    params = Append_Str(params, this.Calc_Radius(), s);

    return "arc("+params+")";
  }

  Calc_Radius()
  {
    return Math.hypot(this.rp.x, this.rp.y);
  }

  Render(ctx)
  {
    super.Render(ctx);
    ctx.arcTo(this.pt.x, this.pt.y, this.cp.x, this.cp.y, this.Calc_Radius());
  }

  Render_Design(ctx)
  {
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = "#aaa";
    ctx.setLineDash([5, 5]);

    ctx.moveTo(this.prev_shape.pt.x, this.prev_shape.pt.y);
    ctx.lineTo(this.cp.x, this.cp.y);
    ctx.lineTo(this.pt.x, this.pt.y);

    ctx.moveTo(0, 0);
    ctx.lineTo(this.rp.x, this.rp.y);

    ctx.stroke();
    ctx.restore();
  }
}

export class Shape_QuadraticCurveTo extends Shape
{
  constructor()
  {
    super();
    this.cp = this.New_Btn_Path("cp", 100, 100);
  }

  Params_Str()
  {
    const s = ", ";
    let res = "";

    res = Append_Str(res, "cpx = "+Round(this.cp.x), s);
    res = Append_Str(res, "cpy = "+Round(this.cp.y), s);
    res = Append_Str(res, "x = "+Round(this.pt.x), s);
    res = Append_Str(res, "y = "+Round(this.pt.y), s);

    return res;
  }

  To_Cmd_Str()
  {
    const s = ", ";
    let params;

    params = Append_Str(params, this.cp.x, s);
    params = Append_Str(params, this.cp.y, s);
    params = Append_Str(params, this.pt.x, s);
    params = Append_Str(params, this.pt.y, s);

    return "quadraticCurveTo("+params+")";
  }

  Render(ctx)
  {
    super.Render(ctx);
    ctx.quadraticCurveTo(this.cp.x, this.cp.y, this.pt.x, this.pt.y);
  }

  Render_Design(ctx)
  {
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = "#aaa";
    ctx.setLineDash([5, 5]);
    ctx.moveTo(this.prev_shape.pt.x, this.prev_shape.pt.y);
    ctx.lineTo(this.cp.x, this.cp.y);
    ctx.lineTo(this.pt.x, this.pt.y);
    ctx.stroke();
    ctx.restore();
  }
}

export class Shape_BezierCurveTo extends Shape
{
  constructor()
  {
    super();
    this.cp1 = this.New_Btn_Path("cp1", -100, -100);
    this.cp2 = this.New_Btn_Path("cp2", 100, 100);
  }

  Params_Str()
  {
    const s = ", ";
    let res = "";

    res = Append_Str(res, "cp1x = "+Round(this.cp1.x), s);
    res = Append_Str(res, "cp1y = "+Round(this.cp1.y), s);
    res = Append_Str(res, "cp2x = "+Round(this.cp2.x), s);
    res = Append_Str(res, "cp2y = "+Round(this.cp2.y), s);
    res = Append_Str(res, "<br>x = "+Round(this.pt.x), s);
    res = Append_Str(res, "y = "+Round(this.pt.x), s);

    return res;
  }

  To_Cmd_Str()
  {
    const s = ", ";
    let params;

    params = Append_Str(params, this.cp1.x, s);
    params = Append_Str(params, this.cp1.y, s);
    params = Append_Str(params, this.cp2.x, s);
    params = Append_Str(params, this.cp2.y, s);
    params = Append_Str(params, this.pt.x, s);
    params = Append_Str(params, this.pt.y, s);

    return "bezierCurveTo("+params+")";
  }

  Render(ctx)
  {
    super.Render(ctx);
    ctx.bezierCurveTo(this.cp1.x, this.cp1.y, this.cp2.x, this.cp2.y, this.pt.x, this.pt.y);
  }

  Render_Design(ctx)
  {
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = "#aaa";
    ctx.setLineDash([5, 5]);
    ctx.moveTo(this.prev_shape.pt.x, this.prev_shape.pt.y);
    ctx.lineTo(this.cp1.x, this.cp1.y);
    ctx.moveTo(this.cp2.x, this.cp2.y);
    ctx.lineTo(this.pt.x, this.pt.y);
    ctx.stroke();
    ctx.restore();
  }
}

export class Shape_LineTo extends Shape
{

  To_Cmd_Str()
  {
    const s = ", ";
    let params;

    params = Append_Str(params, this.pt.x, s);
    params = Append_Str(params, this.pt.y, s);

    return "lineTo("+params+")";
  }
  Render(ctx)
  {
    super.Render(ctx);
    ctx.lineTo(this.pt.x, this.pt.y);
  }
}

export class Shape_MoveTo extends Shape
{
  Render(ctx)
  {
    super.Render(ctx);
    ctx.moveTo(this.pt.x, this.pt.y);
  }
}

// Utils ==========================================================================================

export function Render(plants)
{
  var c, plant;

  for (c = 0; c < plants.length; c++)
  {
    plant = plants[c];
    plant.canvas_ctx.clearRect(0, 0, plant.canvas_ctx.canvas.width, plant.canvas_ctx.canvas.height);
    plant.Render_All();
  }
};

function Random(base, delta)
{
  return base + (Math.random() - 0.5) * delta;
}

export function Append_Str(a, b, sep)
{
  let res = "";

  if (!Empty(a) && !Empty(b))
  {
    res = a+sep+b;
  }
  else if (Empty(a) && !Empty(b))
  {
    res = b;
  }
  else if (!Empty(a) && Empty(b))
  {
    res = a;
  }

  return res;
}

function Empty(v)
{
  const res = v == null || v == undefined || v === "";
  return res;
}

function Round(num)
{
  return Math.round((num + Number.EPSILON) * 10000) / 10000;
}
