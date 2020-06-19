import Canvas_Editor from "../Canvas_Editor.js";

// Editor Def ======================================================================================

class Game_Obj_Editor 
extends Canvas_Editor
{
  Render(ctx, objs)
  {
    let obj;

    this.Clr(ctx);
    this.Render_Origin(ctx);

    if (objs && objs.length>0)
    {
      for (let i=0; i<objs.length; i++)
      {
        obj = objs[i];

        ctx.save();
        ctx.translate(obj.pt.x, obj.pt.y);
        ctx.rotate(obj.angle);
        ctx.scale(obj.scale.x, obj.scale.y);
        const avg_scale = (Math.abs(obj.scale.x) + Math.abs(obj.scale.y)) / 2;
        const lineWidth = this.zoom[this.ctx.zoom_id].lineWidth;
        ctx.lineWidth = lineWidth / avg_scale;

        obj.Render(ctx);
        if (obj.selected && obj.Render_Design)
        {
          obj.Render_Design(ctx);
        }

        ctx.restore();
      }
    }
  }
}

customElements.define('game-obj-editor', Game_Obj_Editor);
export default Game_Obj_Editor;