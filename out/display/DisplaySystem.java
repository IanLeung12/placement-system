import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Frame;
import java.awt.Dimension;

/**
 * DisplaySystem
 * The system which displays the given warehouse
 */
public class DisplaySystem {
    private final JFrame graphicsFrame;
    private JFrame currentTruckFrame;
    private final Warehouse warehouse;
    private Truck currentTruck;
    private JPanel buttonPanel;
    private JScrollPane boxPlacementScrollPane;
    private JScrollPane boxInfoScrollPane;
    private Box currentBox;
    private JButton currentJButton;
    private int borderWidth;
    private double scaleFactor;
    private int pageIndex = 0;
    private PlacementSystem placementSystem;

    DisplaySystem(Warehouse warehouse, PlacementSystem placementSystem) {
        this.warehouse = warehouse;
        this.placementSystem = placementSystem;
        graphicsFrame = new JFrame();
        graphicsFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        graphicsFrame.setLayout(new BorderLayout());
        graphicsFrame.setBackground(Color.DARK_GRAY);
        graphicsFrame.setState(JFrame.MAXIMIZED_BOTH);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        graphicsFrame.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * runLoop
     * starts the display of the truck
     */
    public void runLoop() {
        JLabel titleLabel = new JLabel("Warehouse Display", SwingConstants.CENTER);
        titleLabel.setFont(new Font("serif", Font.PLAIN, 39));
        graphicsFrame.add(titleLabel, BorderLayout.NORTH);
        createBoxInfoButtonList();
        JPanel pagesPanel = new JPanel();
        JButton lastPageButton = new JButton("back");
        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pageIndex > 0){
                    for (int i2 = pageIndex * 5; i2 < (pageIndex + 1) * 5;  i2++){
                        if (i2 <= warehouse.getTrucks().size() - 1) {
                            warehouse.getTrucks().get(i2).removeButton(buttonPanel);
                        }
                    }
                    if (pageIndex - 1  >= 0) {
                        pageIndex--;
                        for (int i2 = pageIndex * 5; i2 < (pageIndex + 1) * 5; i2++) {
                            if (i2 > -1 && i2 <= warehouse.getTrucks().size() - 1) {
                                createTruckGUI(i2);
                                graphicsFrame.revalidate();
                                graphicsFrame.repaint();
                            }
                        }
                    }
                }
            }
        });

        pagesPanel.add(lastPageButton);
        JButton nextPageButton = new JButton("next");
        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!((pageIndex + 1) * 5 >= warehouse.getTrucks().size())) {
                    for (int i2 = pageIndex; i2 < (pageIndex + 1) * 5; i2++) {
                        if (i2 <= warehouse.getTrucks().size() - 1) {
                            warehouse.getTrucks().get(i2).removeButton(buttonPanel);
                        }
                    }
                    pageIndex++;
                    for (int i2 = (pageIndex) * 5; i2 < (pageIndex + 1) * 5; i2++) {
                        if (i2 <= warehouse.getTrucks().size() - 1) {
                            createTruckGUI(i2);
                            graphicsFrame.revalidate();
                            graphicsFrame.repaint();
                        }
                    }
                }
                graphicsFrame.revalidate();
                graphicsFrame.repaint();
            }
        });
        pagesPanel.add(nextPageButton);
        graphicsFrame.add(pagesPanel, BorderLayout.SOUTH);
        if (warehouse.getTrucks().size() <= 5) {
            for (int i = 0; i < warehouse.getTrucks().size(); i++) {
                createTruckGUI(i);
            }
        } else {
            for (int i = 0; i < 5; i ++){
                createTruckGUI(i);
            }
        }
        graphicsFrame.setVisible(true);
    }

    class GraphicsPanel extends JPanel {
        private JButton backButton;
        private JButton autoPlaceButton;
        GraphicsPanel() {
            this.setLayout(new BorderLayout());
            backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentTruckFrame.setFocusable(true);
                    currentTruckFrame.setVisible(false);
                    graphicsFrame.setVisible(true);
                }
            });
            this.add(backButton, BorderLayout.SOUTH);
            this.setVisible(true);
            autoPlaceButton = new JButton("Auto place boxes");
            autoPlaceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Calls method from placement system
                    placementSystem.loadBoxesToTruck(warehouse.getInventory(), currentTruck);
                    updateBoxInfoButtonList();
                    updateBoxPlaceList();
                }
            });
            this.add(autoPlaceButton, BorderLayout.SOUTH);
        }

        /**
         * paintComponent
         * updates the display
         * @param g Graphics object
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //Drawing background for chosen truck
            int screenHeight = ((int) Math.round(getSize().getHeight()));
            int screenWidth = ((int) Math.round(getSize().getWidth()));
            int verticalOffset = ((int) Math.round(screenHeight / 10.0));
            int horizontalOffset;
            //truck length == screen height
            int scaledTruckLength;
            int scaledTruckWidth;
            borderWidth = ((int) Math.round(screenHeight / 100.0));

            //ratio: width/height
            double screenRatio = screenWidth / ((double) (screenHeight - verticalOffset));

            //ratio: width/length
            double truckRatio = currentTruck.getWidth() / ((double) currentTruck.getLength());

            if (screenRatio > truckRatio) {
                scaledTruckLength = screenHeight - verticalOffset - (2 * borderWidth);
                scaleFactor = scaledTruckLength / ((double) currentTruck.getLength());
                scaledTruckWidth = ((int) Math.round(currentTruck.getWidth() * scaleFactor));
                horizontalOffset = screenWidth - scaledTruckWidth - (2 * borderWidth);
            } else {
                horizontalOffset = 0;
                scaledTruckWidth = screenWidth - (2 * borderWidth);
                scaleFactor = scaledTruckWidth / ((double) currentTruck.getWidth());
                scaledTruckLength = ((int) Math.round(currentTruck.getLength() * scaleFactor));
                verticalOffset = screenHeight - scaledTruckLength - (2 * borderWidth);
            }

            backButton.setBounds(0, screenHeight - verticalOffset, ((int)Math.round(screenWidth/2.0)), verticalOffset);
            autoPlaceButton.setBounds(((int)Math.round(screenWidth/2.0)), screenHeight - verticalOffset, ((int)Math.round(screenWidth/2.0)), verticalOffset);

            g.setColor(new Color(212, 210, 210));
            g.fillRect(0, 0, scaledTruckWidth + (2 * borderWidth), scaledTruckLength + (2 * borderWidth));
            g.setColor(new Color(191, 188, 187));
            g.fillRect(borderWidth, borderWidth, scaledTruckWidth, scaledTruckLength);

            drawBoxes(g, borderWidth, scaleFactor, currentTruck.getBoxes());

            //Draw the box being moved at the moment
            if (currentBox != null) {
                //Draws box right on cursor location
                currentBox.draw(g, MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y, scaleFactor, borderWidth);
            }
            repaint();
        }

        /**
         * drawBoxes
         * Draws all the boxes in the given arraylist
         * @param g                Graphics component from GraphicsPanel
         * @param borderWidth      int, the width of the line border around truck
         * @param scaleFactor      double, the scale factor for the dimensions of the truck and boxes to pixels on the screen
         * @param boxes            ArrayList, list of boxes
         */
        public void drawBoxes(Graphics g, int borderWidth, double scaleFactor, ArrayList<Box> boxes) {
            Box currentBox;
            for (int i = 0; i < boxes.size(); i++) {
                currentBox = boxes.get(i);
                currentBox.draw(g, borderWidth + ((int)Math.round(currentBox.getPositionXInTruck() * scaleFactor)), borderWidth + ((int) Math.round(currentBox.getPositionYInTruck() * scaleFactor)), scaleFactor, borderWidth);
            }
        }
    }

    /**
     * updateBoxPlaceList
     * updates the box place list by deleting and recreating it
     */
    public void updateBoxPlaceList() {
        currentTruckFrame.remove(boxPlacementScrollPane);
        createBoxPlacementButtonList();
        currentTruckFrame.revalidate();
        currentTruckFrame.repaint();
    }

    public void createBoxPlacementButtonList() {
        int listWidth = ((int)Math.round(currentTruckFrame.getWidth() / 12.0));

        JPanel tempButtonPanel = new JPanel();
        tempButtonPanel.setLayout(new BoxLayout(tempButtonPanel,BoxLayout.Y_AXIS));
        tempButtonPanel.setBorder(BorderFactory.createTitledBorder("Unplaced boxes:"));
        tempButtonPanel.setPreferredSize(new Dimension(listWidth , currentTruckFrame.getHeight()));

        for (int i = 0; i < warehouse.getInventory().size(); i++){
            Box box = warehouse.getInventory().get(i);
            JButton button = new JButton("Box " + box.getBoxID());
            button.addActionListener(new BoxPlacementButtonListener(box));
            tempButtonPanel.add(button);
        }

        boxPlacementScrollPane = new JScrollPane(tempButtonPanel);
        boxPlacementScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        boxPlacementScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        currentTruckFrame.add(boxPlacementScrollPane, BorderLayout.EAST);
    }

    public void updateBoxInfoButtonList() {
        graphicsFrame.remove(boxInfoScrollPane);
        createBoxInfoButtonList();
        graphicsFrame.revalidate();
        graphicsFrame.repaint();
    }

    public void createBoxInfoButtonList() {
        int listWidth = ((int)Math.round(graphicsFrame.getWidth() / 12.0));

        JPanel tempButtonPanel = new JPanel();
        tempButtonPanel.setLayout(new BoxLayout(tempButtonPanel,BoxLayout.Y_AXIS));
        tempButtonPanel.setBorder(BorderFactory.createTitledBorder("Unplaced boxes:"));
        tempButtonPanel.setPreferredSize(new Dimension(listWidth , graphicsFrame.getHeight()));

        for (int i = 0; i < warehouse.getInventory().size(); i++){
            Box box = warehouse.getInventory().get(i);
            JButton button = new JButton("Box " + box.getBoxID());
            button.addActionListener(new BoxInfoButtonListener(box));
            tempButtonPanel.add(button);
        }

        boxInfoScrollPane = new JScrollPane(tempButtonPanel);
        boxInfoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        boxInfoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        graphicsFrame.add(boxInfoScrollPane, BorderLayout.EAST);
    }

    /**
     * canPlace
     * temporary replacement for a method in placement system that detects whether a box can be place in a certain location
     * @param box
     * @param truck
     * @param x
     * @param y
     * @return
     */
    public boolean canPlace(Box box, Truck truck, int x, int y) {
        return true;
    }

    /**
     * createTruckGUI
     * creates the GUI Components of each seperate Truck, and adds the gui to the main Frame
     * @param index int index
     */
    public void createTruckGUI(int index1) {
      final int index = index1;
        warehouse.getTrucks().get(index).drawList(buttonPanel).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTruck = warehouse.getTrucks().get(index);
                currentTruckFrame = new JFrame("Truck " + currentTruck.getTruckID());
                currentTruckFrame.setUndecorated(false);
                currentTruckFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                currentTruckFrame.setState(Frame.MAXIMIZED_BOTH);//ideally sets the frame to full screen
                final GraphicsPanel graphicsPanel = new GraphicsPanel();

                graphicsPanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (currentBox != null) {
                            int unscaledX = ((int)Math.round((e.getLocationOnScreen().x - graphicsPanel.getLocationOnScreen().x - borderWidth) / scaleFactor));
                            int unscaledY = ((int)Math.round((e.getLocationOnScreen().y - graphicsPanel.getLocationOnScreen().y - borderWidth) / scaleFactor));

                            if (((currentTruck.getLength() - unscaledY) - currentBox.getLength() >= 0) && ((currentTruck.getLength() - unscaledY) - currentBox.getLength() < currentTruck.getLength()) && (unscaledX >= 0) && (unscaledX < currentTruck.getWidth())) {
                                if (placementSystem.loadBoxToTruck(currentBox, currentTruck, (currentTruck.getLength() - unscaledY) - currentBox.getLength(), unscaledX)) {
                                    warehouse.getInventory().remove(warehouse.findBox(currentBox.getBoxID()));
                                    //currentBox.setPositionXInTruck(unscaledX);
                                    //currentBox.setPositionYInTruck(unscaledY);
                                    updateBoxPlaceList();
                                    updateBoxInfoButtonList();
                                }
                            }
                            currentBox = null;
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                });

                currentTruckFrame.add(graphicsPanel);
                currentTruckFrame.setVisible(true);
                createBoxPlacementButtonList();
            }
        });
        buttonPanel.repaint();
    }

    class BoxInfoButtonListener implements ActionListener {
        private Box box;
        BoxInfoButtonListener(Box box) {
            this.box = box;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame boxInfoFrame = new JFrame("Box Info");
            String boxInfo = "<html>Box ID: " + box.getBoxID() + "<br>weight: " + box.getWeight() + "<br>height: " + box.getHeight() + "<br>length: " + box.getLength() + "<br>width:" + box.getWeight() + "</html>";
            JLabel boxInfoLabel = new JLabel(boxInfo);
            boxInfoFrame.add(boxInfoLabel);
            boxInfoFrame.setSize(350,150);
            boxInfoFrame.setVisible(true);
        }
    }

    class BoxPlacementButtonListener implements ActionListener {
        private Box box;
        BoxPlacementButtonListener(Box box) {
            this.box = box;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            currentBox = box;
        }
    }
}