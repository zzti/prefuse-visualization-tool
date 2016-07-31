package org.apache.Jack1118_1130;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.assignment.DataShapeAction;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.ControlAdapter;
import prefuse.controls.DragControl;
import prefuse.controls.FocusControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.PolygonRenderer;
import prefuse.render.Renderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
/**
 * 手动创建点、边，并构成一个图形，测试Display方法,包括平移、设置背景图像
 * @author Jack
 *
 */
public class Example1126{

    
    public static void main(String[] argv) {
    	Visualization vis = new Visualization();
    	Graph g = new Graph();
    	for(int i = 0; i<3; i++){
    		Node n1 = g.addNode();
    		Node n2 = g.addNode();
    		Node n3 = g.addNode();
    		g.addEdge(n1, n2);
    		g.addEdge(n2, n3);
    		g.addEdge(n3, n1);
    	}
    	g.addEdge(0, 3);
    	g.addEdge(3, 6);
    	g.addEdge(6, 0);
    	
    	vis.add("graph", g);
    	ShapeRenderer renderer = new ShapeRenderer(10);
    	vis.setRendererFactory(new DefaultRendererFactory(renderer));
    	
//    	ColorAction nodeText = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR, ColorLib.gray(80));
    	ColorAction nodeFill = new ColorAction("graph.nodes", VisualItem.FILLCOLOR, ColorLib.rgb(10, 150, 220));
		ColorAction edgesStroke = new ColorAction("graph.edges", VisualItem.STROKECOLOR, ColorLib.rgb(100, 80, 180));
		ColorAction nodeHighlight = new ColorAction("graph.nodes", VisualItem.HIGHLIGHT, ColorLib.rgb(10, 150, 220));
		
		ActionList color = new ActionList();
//		color.add(nodeText);
		color.add(nodeFill);
		color.add(edgesStroke);
		
		ActionList layout = new ActionList(Activity.INFINITY);
        layout.add(color);
        layout.add(new ForceDirectedLayout("graph"));
        layout.add(new RepaintAction());
		
        
		Display display = new Display(vis);
		display.setSize(400, 500);
		display.pan(250, 250);
		display.animatePanAbs(230, 220, 2000);
//		display.animatePan(50, 50, 1000);
		System.out.println(display.getTransform());
		Rectangle r = new Rectangle(200,150,150,150);
		
		int[] x = new int[]{30,20,25,35,40};
		int[] y = new int[]{30,40,50,40,30};
		Polygon p = new Polygon(x,y,5);
		Image image = Toolkit.getDefaultToolkit().getImage("F:\\博客图片\\可视化\\Cytoscape.jpg");
		display.setBackgroundImage(image, true, false);
		
		display.editText("jackasjkdfjlajdslfjaldjf;ldjsfjfla", r);//添加一个Rectangle类并赋给text值
		display.addControlListener(new DragControl());
		display.addControlListener(new PanControl());
		display.addControlListener(new ZoomControl());
		display.addControlListener(new WheelZoomControl());
		display.addControlListener(new FocusControl(1));
		display.addControlListener(new ZoomToFitControl());
		
		vis.putAction("color", color);
		vis.putAction("layout", layout);
    	
    	
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 700);
        frame.add(display);
        frame.setVisible(true);
        
        vis.run("color");
        vis.run("layout");
    }
    
} // end of class AggregateDemo





