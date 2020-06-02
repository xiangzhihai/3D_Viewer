import ij.IJ;
import ij.ImagePlus;
import ij.Prefs;
import ij.plugin.Concatenator;
import ij.plugin.FolderOpener;
import ij.process.StackConverter;
import ij3d.Content;
import ij3d.Image3DUniverse;
import ij3d.behaviors.InteractiveBehavior;
import org.scijava.vecmath.Point3d;
import org.scijava.vecmath.Point3f;
import view4d.Timeline;
import voltex.VoltexGroup;

import java.awt.event.MouseEvent;
import java.io.File;

public class myMain {
    public static void main(String[] args) {
        myMain m = new myMain();
        m.run();

    }

    private void run () {
        new ij.ImageJ();
        String path = "C:\\Users\\Richard\\Desktop\\dcms\\";
//        "/Users/zhihaixiang/Documents/GitHub/CereFlow_IJ/icbm_avg_high"
        File f = new File("/Users/zhihaixiang/Desktop/dcms");


        int size = 20;
        ImagePlus[] images = new ImagePlus[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            String formatted = String.format("%0"+ Integer.toString(4 - i/10) + "d", 0);
            String p = path + formatted + Integer.toString(i) + ".dcm";
            ImagePlus ip = IJ.openImage(p);
            new StackConverter(ip).convertToGray8();
            images[count++] = ip;
        }

        ImagePlus imp =  (new Concatenator()).concatenate(images, true);



//        Prefs.getImagesURL() + "organ-of-corti.zip"
//        ImagePlus imp = FolderOpener.open("/Users/zhihaixiang/Desktop/dcms");
//        new StackConverter(imp).convertToGray8();
//        imp.show();
        // Create a universe and show it
        Image3DUniverse univ = new Image3DUniverse();
        univ.setRotationInterval(0.1f);
        univ.show();



        // load the stack in the viewer
        Content c = univ.addVoltex(imp);
        int r[] = new int[256];
        int g[] = new int[256];
        int b[] = new int[256];
        int intArray[] = new int[256];
        c.getAlphaLUT(intArray);


        for (int i=0; i<256; i++) {
            r[i] = fireTable[3*i];
            g[i] = fireTable[3*i+1];
            b[i] = fireTable[3*i+2];
        }
        c.setLUT(r,g,b,intArray);


        univ.setInteractiveBehavior(new CustomBehavior(univ, c));

        Timeline tl = univ.getTimeline();
        tl.setBounceBack(false);
//        tl.play();

//        // Stop animation
//        tl.pause();
    }

    private static final int [] fireTable = { 0,0,31,0,0,31,0,0,33,0,0,35,0,0,37,0,0,39,0,0,41,0,0,43,0,0,45,0,0,47,0,0,49,0,0,52,0,0,54,0,0,57,0,0,59,0,0,62,
            0,0,64,0,0,67,0,0,70,0,0,73,0,0,76,0,0,79,0,0,82,0,0,85,0,0,88,0,0,92,2,0,96,3,0,99,5,0,102,7,0,105,10,0,108,13,0,112,
            15,0,116,17,0,119,20,0,122,22,0,126,25,0,130,28,0,134,31,0,138,33,0,141,35,0,145,38,0,149,41,0,152,43,0,156,46,0,160,49,0,164,52,0,168,55,0,171,
            58,0,175,61,0,178,64,0,181,67,0,184,70,0,188,73,0,191,76,0,195,78,0,198,81,0,202,85,0,205,88,0,209,91,0,212,94,0,216,98,0,218,101,0,220,104,0,221,
            107,0,222,110,0,223,113,0,224,116,0,225,119,0,226,122,0,225,126,0,224,129,0,222,133,0,219,136,0,217,140,0,214,143,0,212,146,0,209,148,0,206,150,0,202,153,0,198,
            155,0,193,158,0,189,160,0,185,162,0,181,163,0,177,165,0,173,166,0,168,168,0,163,170,0,159,171,0,154,173,0,151,174,0,146,176,0,142,178,0,137,179,0,133,181,0,129,
            182,0,125,184,0,120,186,0,116,188,0,111,189,0,107,191,0,103,193,0,98,195,0,94,196,1,89,198,3,85,200,5,80,202,8,76,204,10,71,205,12,67,207,15,63,209,18,58,
            210,21,54,212,24,49,213,27,45,215,31,40,217,34,36,218,37,31,220,40,27,222,44,22,224,48,17,226,51,12,227,54,8,229,58,5,231,61,4,233,65,3,234,68,2,236,72,1,
            238,75,0,240,79,0,241,82,0,243,85,0,245,89,0,247,92,0,249,95,0,250,99,0,251,102,0,252,105,0,253,107,0,253,110,0,253,112,0,254,115,0,255,117,0,255,119,0,
            255,122,0,255,125,0,255,127,0,255,129,0,255,131,0,255,134,0,255,136,0,255,138,0,255,140,0,255,142,0,255,145,0,255,147,0,255,149,0,255,151,0,255,153,0,255,155,0,
            255,157,0,255,159,0,255,161,0,255,163,0,255,166,0,255,168,0,255,169,0,255,171,0,255,173,0,255,176,0,255,178,0,255,180,0,255,182,0,255,184,0,255,186,0,255,189,0,
            255,191,0,255,193,0,255,195,0,255,197,0,255,199,0,255,201,0,255,203,0,255,205,0,255,208,0,255,210,0,255,212,0,255,213,0,255,215,0,255,217,0,255,219,0,255,220,0,
            255,222,0,255,224,0,255,226,0,255,228,0,255,230,0,255,232,1,255,234,3,255,236,6,255,238,10,255,239,14,255,241,18,255,243,22,255,244,27,255,246,31,255,248,37,255,248,43,
            255,249,50,255,250,58,255,251,66,255,252,74,255,253,81,255,254,88,255,255,95,255,255,102,255,255,108,255,255,115,255,255,122,255,255,129,255,255,136,255,255,142,255,255,148,255,255,154,
            255,255,161,255,255,167,255,255,174,255,255,180,255,255,185,255,255,192,255,255,198,255,255,204,255,255,210,255,255,215,255,255,221,255,255,225,255,255,228,255,255,231,255,255,234,255,255,236,
            255,255,239,255,255,242,255,255,244,255,255,247,255,255,249,255,255,251,255,255,253,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255,255
    };

    private class CustomBehavior extends InteractiveBehavior {

        private Content c;

        CustomBehavior(Image3DUniverse univ, Content c) {
            super(univ);
            this.c = c;
        }

        @Override
        public void doProcess(MouseEvent e) {
            if(!e.isControlDown() ||
                    e.getID() != MouseEvent.MOUSE_PRESSED) {
                super.doProcess(e);
                return;
            }
            // Get the point on the geometry where the mouse
            // press occurred
            Point3d p = univ.getPicker().
                    getPickPointGeometry(c, e.getX(), e.getY());
            if(p == null)
                return;

            VoltexGroup voltex = (VoltexGroup)c.getContent();

            System.out.println();
            IJ.showMessage("Picked " + new Point3f(p) + String.valueOf(voltex.getRenderer().getVolume().getAverage((int)Math.round(p.x), (int)Math.round(p.y), (int)Math.round(p.z))& 0xff));
        }
    }
}
