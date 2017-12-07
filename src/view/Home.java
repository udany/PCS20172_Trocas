package view;

import base.BaseModel;
import base.BaseStore;
import base.SyncedListModel;
import controller.AuthController;
import model.*;
import model.enums.Permission;
import model.enums.State;
import util.ArrayListModel;
import util.ListDoubleClickAdapter;
import util.StringCellRenderer;
import util.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private JPanel usersPanel;
    private JList<User> userList;
    private JButton editUserButton;
    private JPanel searchPanel;
    private JButton searchButton;
    private JComboBox<State> stateSelect;
    private JTextField cityField;
    private JTextField neighborhoodField;
    private JComboBox<ProductCategory> categorySelect;
    private JTextField productField;
    private JButton clearSearchButton;


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

        ////// SearchSetup
        searchSetup();

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
            tabSetEnabled(categoryPanel, true);
        } else {
            tabSetEnabled(categoryPanel, false);
        }

        /// Users
        if (current.hasPermission(Permission.UserManagement)) {
            tabSetEnabled(usersPanel, true);
        } else {
            tabSetEnabled(usersPanel, false);
        }

        tabbedPane.setSelectedIndex(0);
    }

    private void searchSetup() {
        /// Fill State
        stateSelect.setModel(new ArrayListModel(Arrays.asList(State.values())));
        stateSelect.setRenderer(new StringCellRenderer<State>(x -> x != null ? x.getAcronym() + " - " + x.getName() : ""));

        /// Fill category
        categorySelect.setModel(new SyncedListModel<>(ProductCategory.store));
        categorySelect.setRenderer(new StringCellRenderer<ProductCategory>(x -> x != null ? x.getName() : ""));

        clearSearchButton.addActionListener(e -> clearSearch());
        searchButton.addActionListener(e -> search());
    }

    private void clearSearch() {
        stateSelect.setSelectedItem(null);
        stateSelect.repaint();
        cityField.setText("");
        neighborhoodField.setText("");

        categorySelect.setSelectedItem(null);
        categorySelect.repaint();
        productField.setText("");
    }

    private void search() {
        List<Counter> counters = Counter.store.List(x -> {
            boolean result = true;

            result &= stateSelect.getSelectedItem() == null || stateSelect.getSelectedItem() == x.getAddress().getState();
            result &= x.getAddress().getCity().contains(cityField.getText());
            result &= x.getAddress().getNeighborhood().contains(neighborhoodField.getText());

            return result;
        });

        List<Product> results = new ArrayList<>();

        for (Counter c : counters) {
            List<Product> products = c.getItems().getList().stream().map(CounterItem::getProduct).collect(Collectors.toList());

            results.addAll(products.stream().filter(x -> {
                boolean result = true;

                result &= categorySelect.getSelectedItem() == null || categorySelect.getSelectedItem() == x.getCategory();
                result &= x.getName().contains(productField.getText());

                return result;
            }).collect(Collectors.toList()));
        }

        ViewBus.get().open(SearchResults.class, results);
    }

    private List<Component> tabs;

    private void tabSetEnabled(Component tab, boolean state) {
        if (tabs == null) tabs = Arrays.asList(tabbedPane.getComponents());
        int idx = tabs.indexOf(tab);
        tabbedPane.setEnabledAt(idx, state);
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
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        tabbedPane.addTab("Busca", searchPanel);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        searchPanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Estado");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        stateSelect = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(stateSelect, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Cidade");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Bairro");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);
        cityField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(cityField, gbc);
        neighborhoodField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(neighborhoodField, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Categoria");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label4, gbc);
        categorySelect = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(categorySelect, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Produto");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label5, gbc);
        productField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(productField, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        searchPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        searchPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        searchPanel.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        searchPanel.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchPanel.add(spacer6, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        searchPanel.add(panel2, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer7, gbc);
        searchButton = new JButton();
        searchButton.setText("Buscar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(searchButton, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer8, gbc);
        clearSearchButton = new JButton();
        clearSearchButton.setText("Limpar");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(clearSearchButton, gbc);
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
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        myCountersPanel.add(panel3, gbc);
        editCounterButton = new JButton();
        editCounterButton.setEnabled(false);
        editCounterButton.setText("Editar");
        editCounterButton.putClientProperty("html.disable", Boolean.FALSE);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(editCounterButton, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer9, gbc);
        newCounterButton = new JButton();
        newCounterButton.setText("Novo");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(newCounterButton, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer10, gbc);
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
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        myProductsPanel.add(panel4, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(spacer12, gbc);
        editProductButton = new JButton();
        editProductButton.setEnabled(false);
        editProductButton.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(editProductButton, gbc);
        newProductButton = new JButton();
        newProductButton.setText("Novo");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(newProductButton, gbc);
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
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        categoryPanel.add(panel5, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(spacer13, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(spacer14, gbc);
        editCategoryButton = new JButton();
        editCategoryButton.setEnabled(false);
        editCategoryButton.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(editCategoryButton, gbc);
        newCategoryButton = new JButton();
        newCategoryButton.setEnabled(true);
        newCategoryButton.setText("Nova");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(newCategoryButton, gbc);
        usersPanel = new JPanel();
        usersPanel.setLayout(new GridBagLayout());
        tabbedPane.addTab("Usuários", usersPanel);
        final JScrollPane scrollPane4 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        usersPanel.add(scrollPane4, gbc);
        userList = new JList();
        scrollPane4.setViewportView(userList);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        usersPanel.add(panel6, gbc);
        editUserButton = new JButton();
        editUserButton.setEnabled(false);
        editUserButton.setText("Editar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(editUserButton, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(spacer15, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
