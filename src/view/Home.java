package view;

import base.BaseModel;
import base.BaseStore;
import base.SyncedListModel;
import controller.AuthController;
import model.*;
import model.enums.Permission;
import sun.java2d.cmm.Profile;
import util.ListDoubleClickAdapter;
import util.StringCellRenderer;
import util.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Home extends MyFrame {
    private JPanel mainPanel;

    private JTabbedPane tabbedPane;

    private JPanel myCountersPanel;
    private JList<Counter> counterList;
    private JButton newCounterButton;
    private JButton editCounterButton;

    private JPanel myProductsPanel;
    private JList<Product> productList;
    private JButton newProductButton;
    private JButton editProductButton;

    private JPanel categoryPanel;
    private JList<ProductCategory> categoryList;
    private JButton newCategoryButton;
    private JButton editCategoryButton;

    private JPanel usersPane;
    private JList<User> userList;
    private JButton editUserButton;


    private SyncedListModel<Counter> countersModel = new SyncedListModel<>(null, null);
    private SyncedListModel<Product> productsModel = new SyncedListModel<>(null, null);
    private SyncedListModel<ProductCategory> categoriesModel = new SyncedListModel<>(null, null);
    private SyncedListModel<User> usersModel = new SyncedListModel<>(null, null);

    public Home() {
        super();

        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(mainPanel);
        createMenu();

        ////// Counters
        countersSetup();

        ////// Products
        productsSetup();

        ////// Categories
        categorySteup();

        ////// Users
        userSetup();

        ////// On Open
        onOpen.addListener(e -> {
            centerOnScreen();
            fill();
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


        ///// Verify Permission
        /// Categories
        if (current.hasPermission(Permission.CategoryManagement)) {
            tabbedPane.setEnabledAt(2, true);
        } else {
            tabbedPane.setEnabledAt(2, false);
        }

        /// Users
        if (current.hasPermission(Permission.UserManagement)) {
            tabbedPane.setEnabledAt(3, true);
        } else {
            tabbedPane.setEnabledAt(3, false);
        }
    }

    private <E extends BaseModel> void setupCrud(
            SyncedListModel<E> model,
            BaseStore<E> store,
            StringCellRenderer<E> renderer,

            JList<E> list,
            JButton newButton,
            JButton editButton,
            Class editorView
    ) {
        // Model
        model.setQuery(x -> x != null);
        model.setStore(store);

        /// List
        list.setModel(model);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(e -> {
            editButton.setEnabled(true);
        });

        list.addMouseListener(new ListDoubleClickAdapter<E>(x -> {
            ViewBus.get().open(editorView, x);
        }));

        list.setCellRenderer(renderer);

        /// New
        if (newButton != null) {
            newButton.addActionListener(e -> {
                ViewBus.get().open(editorView);
            });
        }

        /// Edit
        editButton.addActionListener(e -> {
            ViewBus.get().open(editorView, list.getSelectedValue());
        });
    }

    private void countersSetup() {
        setupCrud(
                countersModel,
                Counter.store,
                new StringCellRenderer<Counter>(x -> x.getId() + " - " + x.getName()),

                counterList,
                newCounterButton,
                editCounterButton,
                CounterEditor.class);
    }

    private void productsSetup() {
        setupCrud(
                productsModel,
                Product.store,
                new StringCellRenderer<Product>(x -> x.getId() + " - " + x.getName()),

                productList,
                newProductButton,
                editProductButton,
                ProductEditor.class);
    }

    private void categorySteup() {
        setupCrud(
                categoriesModel,
                ProductCategory.store,
                new StringCellRenderer<ProductCategory>(x -> x.getId() + " - " + x.getName()),

                categoryList,
                newCategoryButton,
                editCategoryButton,
                CategoryEditor.class);
    }

    private void userSetup() {
        setupCrud(
                usersModel,
                User.store,
                new StringCellRenderer<User>(x -> x.getId() + " - " + x.getName()),

                userList,
                null,
                editUserButton,
                UserEditor.class);
    }

    private void createMenu() {
        Home ref = this;
        //create a menu bar
        final JMenuBar menuBar = new JMenuBar();

        //create menus
        JMenu fileMenu = new JMenu("Arquivo");

        //create menu items
        JMenuItem profileMenuItem = new JMenuItem(new AbstractAction("Editar meu perfil") {
            public void actionPerformed(ActionEvent ae) {
                ViewBus.get().open(UserEditor.class, AuthController.getCurrentUser());
            }
        });
        fileMenu.add(profileMenuItem);

        JMenuItem exitMenuItem = new JMenuItem(new AbstractAction("Sair") {
            public void actionPerformed(ActionEvent ae) {
                ref.close();

                ViewBus.get().open(Login.class);
            }
        });
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
        tabbedPane = new JTabbedPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(tabbedPane, gbc);
        myCountersPanel = new JPanel();
        myCountersPanel.setLayout(new GridBagLayout());
        tabbedPane.addTab("Meus Balcões", myCountersPanel);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        myCountersPanel.add(scrollPane1, gbc);
        counterList = new JList();
        scrollPane1.setViewportView(counterList);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        myCountersPanel.add(panel1, gbc);
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
        myProductsPanel = new JPanel();
        myProductsPanel.setLayout(new GridBagLayout());
        tabbedPane.addTab("Meus Produtos", myProductsPanel);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        myProductsPanel.add(scrollPane2, gbc);
        productList = new JList();
        scrollPane2.setViewportView(productList);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        myProductsPanel.add(panel2, gbc);
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
        categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridBagLayout());
        tabbedPane.addTab("Categorias", categoryPanel);
        final JScrollPane scrollPane3 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        categoryPanel.add(scrollPane3, gbc);
        categoryList = new JList();
        scrollPane3.setViewportView(categoryList);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        categoryPanel.add(panel3, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer6, gbc);
        editCategoryButton = new JButton();
        editCategoryButton.setEnabled(false);
        editCategoryButton.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(editCategoryButton, gbc);
        newCategoryButton = new JButton();
        newCategoryButton.setEnabled(true);
        newCategoryButton.setText("Nova");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(newCategoryButton, gbc);
        usersPane = new JPanel();
        usersPane.setLayout(new GridBagLayout());
        tabbedPane.addTab("Usuários", usersPane);
        final JScrollPane scrollPane4 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        usersPane.add(scrollPane4, gbc);
        userList = new JList();
        scrollPane4.setViewportView(userList);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        usersPane.add(panel4, gbc);
        editUserButton = new JButton();
        editUserButton.setEnabled(false);
        editUserButton.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(editUserButton, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(spacer7, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
