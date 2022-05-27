/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UITParking.GUI;

import UITParking.DTO.DanhMucBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class ChuyenManHinhController {

    private JPanel root;
    private String kindSelected = "";

    private ArrayList<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) throws Exception {
        kindSelected = "QLKH";
        jpnItem.setBackground(Color.red);
        jlbItem.setBackground(Color.red);

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new QLKHJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(ArrayList<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;

        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "QLKH":
                {
                    try {
                        node = new QLKHJPanel();
                    } catch (Exception ex) {
                        Logger.getLogger(ChuyenManHinhController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "QLX":
                    node = new QLXJPanel();
                    break;
                case "QLV":
                    node = new QLVJPanel();
                    break;
                case "QLHD": 
                    node = new QLHDJPanel();
                    break;
                case "QLDT": 
                    node = new QLDTJPanel();
                    break;
                case "BCTK":
                    node = new BCTKJPanel();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(Color.red);
            jlbItem.setBackground(Color.red);

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(Color.red);
            jlbItem.setBackground(Color.red);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(0, 153, 102));
                jlbItem.setBackground(new Color(0, 153, 102));

            }
        }

        private void setChangeBackground(String kind) {
            for (DanhMucBean item : listItem) {
                if (item.getKind().equalsIgnoreCase(kind)) {
                    item.getJpn().setBackground(Color.red);
                    item.getJlb().setBackground(Color.red);
                } else {
                    item.getJpn().setBackground(new Color(0, 153, 102));
                    item.getJlb().setBackground(new Color(0, 153, 102));

                }
            }
        }

    }
}