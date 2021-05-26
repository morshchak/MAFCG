package main;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Scene extends JFrame implements ActionListener, KeyListener {
	Clock clock;
	boolean rotateClock = false;

	public Scene() {
		super("clock");
		clock = new Clock();
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		add(canvas3D);
		canvas3D.addKeyListener(this);
		
		Timer timer = new Timer(75, this);
		timer.start();
		BranchGroup scene = clock.createSceneGraph();
		SimpleUniverse universe = new SimpleUniverse(canvas3D);
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(scene);

		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Scene();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(rotateClock) {
			clock.rotate();
		}
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode() == KeyEvent.VK_ALT) {
			rotateClock = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode() == KeyEvent.VK_ALT) {
			rotateClock = false;
		}
	}
}
