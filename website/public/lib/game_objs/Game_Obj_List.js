import {LitElement, html, css} from "../lit-element/lit-element.js";
import * as pl from "../Coral_Racer.js";
import Object_List from "../Object_List.js";

class Game_Obj_List 
extends Object_List
{
  constructor()
  {
    super();
    this.save_name = "game_objs";
  }

  // Utils ========================================================================================

  Revive_Shape(obj)
  {
    const shape = new pl[obj.class_name];
    Object.assign(shape, obj);
    if (obj.btns && obj.btns.length>0)
    {
      shape.btns = [];
      for (let i=0; i<obj.btns.length; ++i)
      {
        const obj_btn = obj.btns[i];
        const path = shape.New_Btn_Path(obj_btn.id, obj_btn.x, obj_btn.y);
      }
    }
    return shape;
  }

  Add_Shape(class_name, shape_name)
  {
    const shape = new pl[class_name];
    shape.class_name = class_name;
    shape.name = shape_name;

    this.Add(shape);
    this.Select(shape.id);
    this.Save();

    if (this.on_change_fn)
    {
      this.on_change_fn(this.shapes);
    }
  }

  JSON_Replacer(key, value)
  {
    if (key.endsWith("_btn"))
    {
      value = "dynamic";
    }

    return value;
  }

  // Events =======================================================================================

  OnClick_Add_Wall()
  {
    this.Add_Shape("Bouncy_Wall", "Wall");
  }

  OnClick_Add_Player()
  {
    this.Add_Shape("Player", "Player");
  }

  OnClick_Add_Finish()
  {
    this.Add_Shape("Finish", "Finish");
  }

  OnClick_Add_Marker()
  {
    this.Add_Shape("Game_Object", "Marker");
  }

  // Rendering ====================================================================================

  render()
  {
    const btns = html`
    <button class="button" id="new_player" @click="${this.OnClick_Add_Player}" title="Player Character"><img src="images/gamepad-variant-outline.svg"></button>
    <button class="button" id="new_finish" @click="${this.OnClick_Add_Finish}" title="Finish Point"><img src="images/flag-checkered.svg"></button>
    <button class="button" id="new_wall" @click="${this.OnClick_Add_Wall}" title="Wall"><img src="images/wall.svg"></button>
    <button class="button" id="new_marker" @click="${this.OnClick_Add_Marker}" title="Marker"><img src="images/crosshairs-question.svg"></button>
    `;
    return this.Render(btns);
  }
}

customElements.define('game-obj-list', Game_Obj_List);
