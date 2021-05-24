package lab3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PrintingImage extends Application{
	private HeaderBitmapImage image; // �������� ����, ��� ������ ��'��� � ����������� ��� ��������� ����������
	private int numberOfPixels; // �������� ���� ��� ���������� ������� ������ � ������ ��������
	
	public PrintingImage()
	{}
	
	public PrintingImage(HeaderBitmapImage image) // �������������� ����������� �����������
	{
		this.image = image;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ReadingImageFromFile.loadBitmapImage("source/tr5.bmp");
		this.image = ReadingImageFromFile.pr.image;
		int width = (int)this.image.getWidth();
		int height = (int)this.image.getHeight();
		int half = (int)image.getHalfOfWidth();
		Group root = new Group();
		Scene scene = new Scene (root, width, height);
		Circle cir;
		int let = 0;
		int let1 = 0;
		int let2 = 0;
		char[][] map = new char[width][height];
		// �������� ���������� ����� ��� �����
		BufferedInputStream reader = new BufferedInputStream (new FileInputStream("pixels.txt"));
		for(int i=0;i<height;i++) // ���� �� ����� ���������� �� �����
		{ 
			for(int j=0;j<half;j++) // ���� �� ����� ���������� �� ������
				 {
					 let = reader.read(); // ������� ���� ������ � �����
					 let1=let;
					 let2=let; 
					 let1=let1&(0xf0); // ������� ���� - ������ ������
					 let1=let1>>4; // ���� �� 4 ������� 
					 let2=let2&(0x0f); // �������� ���� - ������ ������ 
					 if(j*2<width) // ��� �� 1 ������ ���� 2 ����� ��� ��������� ������ �� �������� ������ ����������
					 { 
						 cir = new Circle ((j)*2,(height-1-i),1, 
						 Color.valueOf((returnPixelColor(let1)))); // �� ��������� ������������ 
						 // �������� ���� ������� � 1 ������ �� �������� ���������� �� ��������� ������ returnPixelColor ������� ������
						 root.getChildren().add(cir); //������ ��'��� � �����
						 if (returnPixelColor(let1) == "BLACK") // ���� ���� ������ ������, �� ������� � ����� 1
						 {
							 map[j*2][height-1-i] = '1';
							 numberOfPixels++; // �������� ������� ������ ������
						 }
						 else
						 {
							 map[j*2][height-1-i] = '0'; 
						 }
					 }
					 if(j*2+1<width) // ��� ������� ������
					 { 
						 cir = new Circle ((j)*2+1,(height-1-
						 i),1,Color.valueOf((returnPixelColor(let2))));
						 root.getChildren().add(cir);
						 if (returnPixelColor(let2) == "BLACK")
						 {
							 map[j*2+1][height-1-i] = '1';
							 numberOfPixels++;
						 }
						 else
						 { 
							 map[j*2+1][height-1-i] = '0'; 
						 }
					 }
				}
		}
		primaryStage.setScene(scene); // ���������� �����
		primaryStage.show(); // ��������� �����
		 
		reader.close();
		BufferedOutputStream writer = new BufferedOutputStream (new
		FileOutputStream("map.txt")); // �������� ����� ��� ���� �� �������� � ����
		for(int i=0;i<height;i++) // ���� �� ����� ���������� �� �����
		{ 
			for(int j=0;j<width;j++) // ���� �� ����� ���������� �� ������
			{
				writer.write(map[j][i]);
			}
			writer.write(10);
		}
		writer.close();
		System.out.println("number of black color pixels = " + numberOfPixels);
		// ��� ��������� ������� ��� ��'���� �� ������ �������
	}
	
	private String returnPixelColor (int color) // ����� ��� ������������ ������� 16-������ ����������
	{
		String col = "BLACK";
		switch(color)
		 {
			 case 0: return "BLACK"; 
			 case 1: return "LIGHTCORAL";
			 case 2: return "GREEN"; 
			 case 3: return "BROWN"; 
			 case 4: return "BLUE"; 
			 case 5: return "MAGENTA"; 
			 case 6: return "CYAN"; 
			 case 7: return "LIGHTGRAY"; 
			 case 8: return "DARKGRAY"; 
			 case 9: return "RED"; 
			 case 10:return "LIGHTGREEN";
			 case 11:return "YELLOW"; 
			 case 12:return "LIGHTBLUE"; 
			 case 13:return "LIGHTPINK"; 
			 case 14:return "LIGHTCYAN"; 
			 case 15:return "WHITE";
		}
		 return col;
	}
	
	public static void main (String args[]) 
	{
		launch(args);
	}
}
