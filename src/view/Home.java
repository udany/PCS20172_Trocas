package view;

import base.SyncedListModel;
import controller.AuthController;
import model.Counter;
import model.Product;
import model.User;
import util.ListDoubleClickAdapter;
import util.StringCellRenderer;
import util.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends MyFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JList counterList;
    private JPanel myCounters;
    private JPanel myProducts;
    private JButton editCounterButton;
    private JButton newCounterButton;
    private JButton editProductButton;
    private JButton newProductButton;
    private JList productList;

    private SyncedListModel<Counter> countersModel;
    private SyncedListModel<Product> productsModel;

    public Home() {
        super();

        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(mainPanel);
        createMenu();

        ////// On Open
        onOpen.addListener(e -> {
            centerOnScreen();
            fill();
        });

        ////// Counters
        /// List
        countersModel = new SyncedListModel<>(x -> x.getUserId() != 0, Counter.store);
        counterList.setModel(countersModel);

        counterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        counterList.addListSelectionListener(e -> {
            editCounterButton.setEnabled(true);
        });
        counterList.addMouseListener(new ListDoubleClickAdapter<Counter>(x -> {
            ViewBus.get().open(CounterEditor.class, x);
        }));

        counterList.setCellRenderer(new StringCellRenderer<Counter>(x -> x.getId() + " - " + x.getName()));

        /// New
        newCounterButton.addActionListener(e -> {
            ViewBus.get().open(CounterEditor.class);
        });

        /// Edit
        editCounterButton.addActionListener(e -> {
            ViewBus.get().open(CounterEditor.class, counterList.getSelectedValue());
        });

        ////// Products
        /// List
        productsModel = new SyncedListModel<>(x -> x.getUserId() != 0, Product.store);
        productList.setModel(productsModel);

        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.addListSelectionListener(e -> {
            editProductButton.setEnabled(true);
        });
        productList.addMouseListener(new ListDoubleClickAdapter<Product>(x -> {
            ViewBus.get().open(ProductEditor.class, x);
        }));

        productList.setCellRenderer(new StringCellRenderer<Product>(x -> x.getId() + " - " + x.getName()));

        /// New
        newProductButton.addActionListener(e -> {
            ViewBus.get().open(ProductEditor.class);
        });

        /// Edit
        editProductButton.addActionListener(e -> {
            ViewBus.get().open(ProductEditor.class, productList.getSelectedValue());
        });
    }

    private void fill() {
        ////// Title Logic
        User current = AuthController.getCurrentUser();
        setTitle("Escamb-o-mator 7000 - Logado como: " + current.getName());

        ////// Counters
        /// List
        countersModel.setQuery(x -> x.getUserId() == current.getId());

        ////// Products
        /// List
        productsModel.setQuery(x -> x.getUserId() == current.getId());
    }

    private void createMenu() {
        Home ref = this;
        //create a menu bar
        final JMenuBar menuBar = new JMenuBar();

        //create menus
        JMenu fileMenu = new JMenu("Arquivo");

        //create menu items
        JMenuItem exitMenuItem = new JMenuItem(new AbstractAction("Sair") {
            public void actionPerformed(ActionEvent ae) {
                ref.close();

                ViewBus.get().open(Login.class);
            }
        });

        //add menu items to menus
        fileMenu.add(exitMenuItem);

        //add menu to menubar
        menuBar.add(fileMenu);

        //add menubar to the frame
        setJMenuBar(menuBar);
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
        editCounterButton = new JButton();
        editCounterButton.setEnabled(false);
        editCounterButton.setText("Editar");
        editCounterButton.putClientProperty("html.disable", Boolean.FALSE);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(editCounterButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        newCounterButton = new JButton();
        newCounterButton.setText("Novo");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(newCounterButton, gbc);
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
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        myProducts.add(scrollPane2, gbc);
        productList = new JList();
        scrollPane2.setViewportView(productList);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        myProducts.add(panel2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer4, gbc);
        editProductButton = new JButton();
        editProductButton.setEnabled(false);
        editProductButton.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(editProductButton, gbc);
        newProductButton = new JButton();
        newProductButton.setText("Novo");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(newProductButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
