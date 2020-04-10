//wap to determine the visibility of the line by finding the outcodes of end file using outcode algorithm and clip a line using cohen sutherland subdivision algorithm
package Cohen_Sutherland;

import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

class ThirdGLEventListener implements GLEventListener {

    int[][] flag = new int[640][480];
    private GLU glu;

    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0, 0, 640, 480);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0, 640, 0, 480);
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        ArrayList<Node> figure = new ArrayList<Node>();
        figure.add(new Node(80, 80));
        figure.add(new Node(250, 230));
        //figure.add(new Node(230, 190));
        //figure.add(new Node(150, 240));
        //figure.add(new Node(70, 190));
        ArrayList<Node> window = new ArrayList<Node>();
        window.add(new Node(100, 100));
        window.add(new Node(200, 100));
        window.add(new Node(200, 200));
        window.add(new Node(100, 200));
        //bottom right top left
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        for (int i = 0; i < 4; i++) {
            drawLine(gl, window.get(i).x, window.get(i).y, window.get((i + 1) % 4).x, window.get((i + 1) % 4).y);
            //drawLine(gl,window.get(i).x+300,window.get(i).y,window.get((i+1)%4).x+300,window.get((i+1)%4).y);
        }
        //y=mx+c
        //x=y/m+c
        gl.glColor3f(0, 1, 0);
        System.out.println(figure.get(0).x + " " + figure.get(0).y + " " + figure.get(1).x + " " + figure.get(1).y);
        drawLine(gl, figure.get(0).x, figure.get(0).y, figure.get(1).x, figure.get(1).y
        );
        float m = (figure.get(1).y - figure.get(0).y) / (figure.get(1).x - figure.get(0).x);
        float cx = figure.get(1).x - figure.get(0).x;
        float cy = figure.get(1).y - figure.get(0).y;
        int x1=figure.get(0).x;
        int y1=figure.get(0).y;
        int x2=figure.get(1).x;
        int y2=figure.get(1).y;
        for (int i = 0; i < figure.size(); i++) {
            //top
            if (figure.get(i).y > window.get(3).y) {
                //gl.glColor3f(1,0 , 0);
                //drawLine(gl,((x2-x1)/(y2-y1)*(window.get(3).y-y1))+y1, window.get(3).y, figure.get(i).x, figure.get(i).y);
                //figure.get(i).y=window.get(3).y;
            }
            //right
            if(figure.get(i).x>window.get(2).x){
               gl.glColor3f(1,0 , 0);
               drawLine(gl, window.get(2).x,(int)m*(window.get(1).x-x1)+y1, figure.get(i).x, figure.get(i).y);
                System.out.println("");
            }
        }
    }

    class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
            int height) {
    }

    public void displayChanged(GLAutoDrawable drawable,
            boolean modeChanged, boolean deviceChanged) {
    }

    private void drawLine(GL gl, int x1, int y1, int x2, int y2) {
        int x, y;
        x = x1;
        y = y1;
        int dx, dy, interchange;
        int e;
        dx = Math.abs(x2 - x1);
        dy = Math.abs(y2 - y1);
        int s1 = 1, s2 = 1;
        if (x2 - x1 < 0) {
            s1 = -1;
        }
        if (y2 - y1 < 0) {
            s2 = -1;
        }
        if (dy > dx) {
            int temp = dx;
            dx = dy;
            dy = temp;
            interchange = 1;
        } else {
            interchange = 0;
        }
        e = (2 * dy) - dx;
        for (int i = 1; i <= dx; i++) {
            // gl.glPointSize(0.3f);
            gl.glBegin(GL.GL_POINTS);
            gl.glVertex2i(x, y);
            // System.out.println(x+" "+y);
            flag[x][y] = 1;
            gl.glFlush();
            while (e > 0) {
                if (interchange == 1) {
                    x = x + s1;
                } else {
                    y = y + s2;
                }
                e = e - (2 * dx);
            }
            if (interchange == 1) {
                y = y + s2;
            } else {
                x = x + s1;
            }
            e = e + (2 * dy);
        }
        gl.glEnd();
    }

    public void dispose(GLAutoDrawable arg0) {
    }
}

public class Cohen_Sutherland {

    public static void main(String args[]) {
        GLCapabilities capabilities = new GLCapabilities();
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        final JFrame frame = new JFrame("Basic frame");
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}
