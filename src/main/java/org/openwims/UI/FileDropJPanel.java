/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openwims.UI;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.TooManyListenersException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author jesse
 */
public class FileDropJPanel extends JPanel {

    private DropTarget dropTarget;
    private DropTargetHandler dropTargetHandler;
    private boolean dragOver = false;
    
    private LinkedList<FilesDraggedListener> listeners;

    public FileDropJPanel() {
        this.listeners = new LinkedList();
    }
    
    public void addFilesDraggedListener(FilesDraggedListener listener) {
        this.listeners.add(listener);
    }

    protected DropTarget getMyDropTarget() {
        if (dropTarget == null) {
            dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, null);
        }
        return dropTarget;
    }

    protected DropTargetHandler getDropTargetHandler() {
        if (dropTargetHandler == null) {
            dropTargetHandler = new DropTargetHandler();
        }
        return dropTargetHandler;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        try {
            getMyDropTarget().addDropTargetListener(getDropTargetHandler());
        } catch (TooManyListenersException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        getMyDropTarget().removeDropTargetListener(getDropTargetHandler());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    
        if (dragOver) {
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {2.0f}, 0.0f));
            g2d.draw(new RoundRectangle2D.Double(10, 10, getWidth() - 20, getHeight() - 20, 10, 10));
            
            String message = "Release to Load and WIMify";
            float messageWidth = g2d.getFontMetrics().stringWidth(message);
            g2d.drawString(message, (getWidth() / 2) - (messageWidth / 2), getHeight() / 2);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {2.0f}, 0.0f));
            g2d.draw(new RoundRectangle2D.Double(10, 10, getWidth() - 20, getHeight() - 20, 10, 10));
            
            String message = "Drag a PP File or Full Process above";
            float messageWidth = g2d.getFontMetrics().stringWidth(message);
            g2d.drawString(message, (getWidth() / 2) - (messageWidth / 2), getHeight() / 2);
        }
        
        g2d.dispose();
    }

    protected void importFiles(final List files) {
        Runnable run = new Runnable() {

            @Override
            public void run() {
                for (FilesDraggedListener listener : listeners) {
                    listener.filesDraggedEvent(files);
                }
            }
        };
        SwingUtilities.invokeLater(run);
    }

    protected class DropTargetHandler implements DropTargetListener {

        protected void processDrag(DropTargetDragEvent dtde) {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            processDrag(dtde);
            SwingUtilities.invokeLater(new DragUpdate(true));
            repaint();
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            processDrag(dtde);
            SwingUtilities.invokeLater(new DragUpdate(true));
            repaint();
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            SwingUtilities.invokeLater(new DragUpdate(false));
            repaint();
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {

            SwingUtilities.invokeLater(new DragUpdate(false));

            Transferable transferable = dtde.getTransferable();
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrop(dtde.getDropAction());
                try {

                    List transferData = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (transferData != null && transferData.size() > 0) {
                        importFiles(transferData);
                        dtde.dropComplete(true);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                dtde.rejectDrop();
            }
        }
    }

    public class DragUpdate implements Runnable {

        private boolean dragOver;

        public DragUpdate(boolean dragOver) {
            this.dragOver = dragOver;
        }

        @Override
        public void run() {
            FileDropJPanel.this.dragOver = dragOver;
            FileDropJPanel.this.repaint();
        }
    }
    
    public interface FilesDraggedListener {
        public void filesDraggedEvent(List<File> files);
    }
}
