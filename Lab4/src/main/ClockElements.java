package main;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class ClockElements {
	public static Primitive getDash() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.1f, 0.01f, 0.01f, primflags, getBlackAppearance());
	}
	
	//задня частина годинника
	public static Primitive getBack() {
		Appearance appearance = new Appearance();
		Color3f emissive = new Color3f(0f, 0f, 0f);
		Color3f ambient = new Color3f(0f, 0f, 0f);
		Color3f diffuse = new Color3f(.3f, .2f, .3f);
		Color3f specular = new Color3f(.7f, .8f, .9f);
		appearance.setMaterial(new Material(ambient, emissive, diffuse, specular, 1f));
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Cylinder(0.6f, 0.2f, primflags, appearance);
	}
	
	//циферблат годинника
	public static Primitive getBase() {
		Appearance appearance = new Appearance();
		Color3f emissive = new Color3f(0f, 0f, 0f);
		Color3f ambient = new Color3f(0f, 0f, 0f);
		Color3f diffuse = new Color3f(.8f, .1f, .2f);
		Color3f specular = new Color3f(.8f, .1f, .2f);
		appearance.setMaterial(new Material(ambient, emissive, diffuse, specular, 1f));
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Cylinder(0.5f, 0.19f, primflags, appearance);
	}

	//годинна стрілка
	public static Primitive getHoursHand() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.15f, 0.03f, 0.01f, primflags, getBlackAppearance());
	}
	
	//хвилинна стрілка	
	public static Primitive getMinutesHand() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.22f, 0.03f, 0.01f, primflags, getBlackAppearance());
	}

	//секундна стрілка
	public static Primitive getSecondsHand() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.25f, 0.02f, 0.01f, primflags, getBlackAppearance());
	}


	public static Appearance getBlackAppearance() {
		Appearance appearance = new Appearance();
		Color3f emissive = new Color3f(0.08f, 0.08f, 0.08f);
		Color3f ambient = new Color3f(0.08f, 0.08f, 0.08f);
		Color3f diffuse = new Color3f(0.2f, 0.2f, 0.2f);
		Color3f specular = new Color3f(0.1f, 0.1f, 0.1f);
		appearance.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.2f));
		return appearance;
	}
}