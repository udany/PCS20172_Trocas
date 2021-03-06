package view;

import controller.AuthController;
import model.Counter;
import model.CounterItem;
import model.Product;
import util.ArrayListModel;
import util.MyFrame;
import util.StringCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CounterEditor extends MyFrame {

    private JPanel mainPanel;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton removeProductButton;
    private JButton addProductButton;
    private JList productList;
    private JButton editAddressButton;
    private JTextArea addressField;

    private Counter currentCounter;
    private ProductSelector productSelector;
    private ArrayListModel<CounterItem> itemModel;

    private AddressEditor addressEditor;

    public CounterEditor() {
        super();

        setTitle("");
        setSize(600, 450);

        setContentPane(mainPanel);

        onOpen.addListener(e -> {
            if (e.data.length > 0 && e.data[0] instanceof Counter) {
                currentCounter = (Counter) e.data[0];
                setTitle("Editando Balcão");
            } else {
                currentCounter = new Counter();
                currentCounter.setUserId(AuthController.getCurrentUser().getId());
                setTitle("Novo Balcão");
            }

            fillForm();

            centerOnScreen();
        });

        saveButton.addActionListener(e -> {
            readForm();
            Counter.store.Save(currentCounter);
            close();
        });
        cancelButton.addActionListener(e -> {
            close();
        });


        /////// Product management
        ////// Products
        itemModel = new ArrayListModel<>(new ArrayList<>());
        productList.setModel(itemModel);

        productList.addListSelectionListener(e -> {
            removeProductButton.setEnabled(true);
        });

        productList.setCellRenderer(new StringCellRenderer<CounterItem>(x -> x.getProduct().getName()));


        ///// Add new
        productSelector = (ProductSelector) ViewBus.get().get(ProductSelector.class);

        addProductButton.addActionListener(e -> {
            productSelector.open(Product.store.List(x -> x.getUserId() == AuthController.getCurrentUser().getId()));

            productSelector.onSelect.addListener(list -> {
                List<CounterItem> ciList = list.stream().map(x -> new CounterItem(x)).collect(Collectors.toList());

                itemModel.add(ciList);
            });
        });

        ///// Remove
        removeProductButton.addActionListener(e -> {
            List<CounterItem> list = productList.getSelectedValuesList();

            itemModel.remove(list);
        });


        /// Edit Address
        addressEditor = (AddressEditor) ViewBus.get().get(AddressEditor.class);
        editAddressButton.addActionListener(e -> {
            addressEditor.open(currentCounter.getAddress());

            addressEditor.onConfirm.addListener(address -> {
                currentCounter.setAddress(address);
                addressField.setText(currentCounter.getAddress().toString());
            });
        });
    }

    private void fillForm() {
        nameField.setText(currentCounter.getName());
        descriptionField.setText(currentCounter.getDescription());
        itemModel.setList(currentCounter.getItems().getList());
        productList.clearSelection();
        addressField.setText(currentCounter.getAddress().toString());
    }

    private void readForm() {
        currentCounter.setName(nameField.getText());
        currentCounter.setDescription(descriptionField.getText());
        currentCounter.getItems().setList(itemModel.getList());
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel1, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Nome");
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
        nameField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(nameField, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Descrição");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel1.add(label2, gbc);
        descriptionField = new JTextArea();
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(descriptionField, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Produtos");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel1.add(label3, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        productList = new JList();
        scrollPane1.setViewportView(productList);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel2, gbc);
        removeProductButton = new JButton();
        removeProductButton.setText("Remover");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(removeProductButton, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer2, gbc);
        addProductButton = new JButton();
        addProductButton.setText("Adicionar");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(addProductButton, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Endereço");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label4, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel3, gbc);
        editAddressButton = new JButton();
        editAddressButton.setText("Editar Endereço");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(editAddressButton, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer4, gbc);
        addressField = new JTextArea();
        addressField.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(addressField, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        mainPanel.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        mainPanel.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        mainPanel.add(spacer8, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel4, gbc);
        saveButton = new JButton();
        saveButton.setText("Salvar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(saveButton, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(spacer9, gbc);
        cancelButton = new JButton();
        cancelButton.setText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(cancelButton, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        mainPanel.add(spacer11, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
