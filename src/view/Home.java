package view;

import base.SyncedList;
import controller.AuthController;
import model.Counter;
import model.User;
import util.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Home extends MyFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JList counterList;
    private JPanel myCounters;
    private JPanel myProducts;
    private JButton editarButton;
    private JButton novoButton;

    public Home() {
        super();

        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(mainPanel);

        ////// Title Logic
        User current = AuthController.getCurrentUser();
        setTitle("Escamb-o-mator 7000 - Logado como: " + current.getName());

        ////// On Open
        onOpen.addListener(e -> {
            centerOnScreen();
        });

        ////// Counters
        List<Counter> counters = new SyncedList<>(x -> x.getUserId() == current.getId(), Counter.store);

        AbstractListModel<Counter> countersModel = new AbstractListModel<Counter>() {
            @Override
            public int getSize() {
                return counters.size();
            }

            @Override
            public Counter getElementAt(int index) {
                return counters.get(index);
            }
        };

        counterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        counterList.setModel(countersModel);


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        tabbedPane1 = new JTabbedPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(tabbedPane1, gbc);
        myCounters = new JPanel();
        myCounters.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Meus Balcões", myCounters);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        myCounters.add(scrollPane1, gbc);
        counterList = new JList();
        scrollPane1.setViewportView(counterList);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        myCounters.add(panel1, gbc);
        editarButton = new JButton();
        editarButton.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(editarButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        novoButton = new JButton();
        novoButton.setText("Novo");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(novoButton, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer2, gbc);
        myProducts = new JPanel();
        myProducts.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Meus Produtos", myProducts);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
