package main;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.util.Date;

import com.sun.j3d.utils.image.TextureLoader;
import java.awt.*;


public class Clock {
	private TransformGroup objectTransformGroup;
	private Transform3D clockTransform3D = new Transform3D();
	private TransformGroup clockTransformGroupSeconds = new TransformGroup();
	private TransformGroup clockTransformGroupMinutes = new TransformGroup();
	private TransformGroup clockTransformGroupHours = new TransformGroup();
	private float angle = 0;

	public BranchGroup createSceneGraph() {
		// створюємо групу об'єктів
		BranchGroup objRoot = new BranchGroup();

		// створюємо об'єкт, що будемо додавати до групи
		objectTransformGroup = new TransformGroup();
		objectTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		buildObject();
		objRoot.addChild(objectTransformGroup);

		TextureLoader loader = new TextureLoader("Lab4/src/honklhonk.jpg", "LUMINANCE", new Container());
        var texture = loader.getImage();

        Background background = new Background(texture);
        background.setImageScaleMode(Background.SCALE_FIT_MAX);
        background.setCapability(Background.ALLOW_IMAGE_WRITE);
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);
		
		// налаштування освітлення
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
		Color3f light1Color = new Color3f(1.0f, 1f, 1f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		objRoot.addChild(light1);

		// встановимо навколишнє освітлення
		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		objRoot.addChild(ambientLightNode);

		return objRoot;
	}

	private void buildObject() {
		double angle = 0;

			Transform3D transform3D = new Transform3D();
			TransformGroup transformGroup = new TransformGroup();
			transform3D.rotX(Math.PI/2);
			transform3D.setTranslation(new Vector3f(0f, 0f, 0.01f));
			transformGroup.setTransform(transform3D);

			transformGroup.addChild(ClockElements.getBase());
			objectTransformGroup.addChild(transformGroup);

			Transform3D transform3D1 = new Transform3D();
			TransformGroup transformGroup1 = new TransformGroup();
			transform3D1.rotX(Math.PI/2);
			transform3D1.setTranslation(new Vector3f(0f, 0f, 0.0f));
			transformGroup1.setTransform(transform3D1);

			transformGroup1.addChild(ClockElements.getBack());
			objectTransformGroup.addChild(transformGroup1);

			clockTransformGroupSeconds.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			clockTransformGroupMinutes.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			clockTransformGroupHours.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			clockTransformGroupSeconds.addChild(ClockElements.getSecondsHand());
			clockTransformGroupMinutes.addChild(ClockElements.getMinutesHand());
			clockTransformGroupHours.addChild(ClockElements.getHoursHand());
			objectTransformGroup.addChild(clockTransformGroupHours);
			objectTransformGroup.addChild(clockTransformGroupMinutes);
			objectTransformGroup.addChild(clockTransformGroupSeconds);
			
			updateClock();

		for(int i = 0; i < 12; ++i) {
			Transform3D transform3D2 = new Transform3D();
			transform3D2.rotZ(angle);
			transform3D2.setTranslation(new Vector3f((float)Math.cos(angle)*.4f, (float)Math.sin(angle)*.4f, 0.1f));
			TransformGroup transformGroup2 = new TransformGroup();
			transformGroup2.setTransform(transform3D2);
			transformGroup2.addChild(ClockElements.getDash());
			angle += Math.PI/6;
			objectTransformGroup.addChild(transformGroup2);
		}

	}

	private static void rotateHand(double angle, float handLength, TransformGroup tg) {
		angle += Math.PI/2;
		Transform3D transform = new Transform3D();
		transform.rotZ(angle);
		transform.setTranslation(new Vector3f((float)Math.cos(angle)*handLength,
				(float)Math.sin(angle)*handLength, 0.1f));
		tg.setTransform(transform);
	}

	public void rotate() {
		clockTransform3D.rotY(angle);
		angle += 0.05;
		objectTransformGroup.setTransform(clockTransform3D);
	}

	public void updateClock() {
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		rotateHand(-Math.PI*2*date.getHours()/12.0, 0.2f, clockTransformGroupHours);
		rotateHand(-Math.PI*2*date.getMinutes()/60.0, 0.25f, clockTransformGroupMinutes);
		rotateHand(-Math.PI*2*date.getSeconds()/60.0, 0.25f, clockTransformGroupSeconds);
	}
}