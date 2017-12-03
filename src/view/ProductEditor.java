package view;

import controller.AuthController;
import model.Product;
import model.ProductCondition;
import util.ArrayListComboModel;
import util.ArrayListModel;
import util.MyFrame;
import util.StringCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ProductEditor extends MyFrame {

    private JPanel mainPanel;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox conditionSelect;

    private Product current;

    public ProductEditor() {
        super();

        setTitle("");
        setSize(600, 450);

        setContentPane(mainPanel);

        /// Fill condition
        conditionSelect.setModel(new ArrayListComboModel(Arrays.asList(ProductCondition.values())));
        conditionSelect.setRenderer(new StringCellRenderer<ProductCondition>(x -> x.getLabel()));

        onOpen.addListener(e -> {
            if (e.data.length > 0 && e.data[0] instanceof Product) {
                current = (Product) e.data[0];
                setTitle("Editando Produto");
            } else {
                current = new Product();
                current.setUserId(AuthController.getCurrentUser().getId());
                setTitle("Novo Produto");
            }

            fillForm();

            centerOnScreen();
        });

        saveButton.addActionListener(e -> {
            readForm();
            Product.store.Save(current);
            close();
        });
        cancelButton.addActionListener(e -> {
            close();
        });
    }

    private void fillForm() {
        nameField.setText(current.getName());
        descriptionField.setText(current.getDescription());
        conditionSelect.setSelectedItem(current.getCondition());
    }

    private void readForm() {
        current.setName(nameField.getText());
        current.setDescription(descriptionField.getText());
        current.setCondition((ProductCondition) conditionSelect.getSelectedItem());
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
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel1.add(label2, gbc);
        descriptionField = new JTextArea();
        descriptionField.setLineWrap(true);
        descriptionField.setText("");
        descriptionField.setWrapStyleWord(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(descriptionField, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Condição");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel1.add(label3, gbc);
        conditionSelect = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(conditionSelect, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        mainPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        mainPanel.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        mainPanel.add(spacer5, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel2, gbc);
        saveButton = new JButton();
        saveButton.setText("Salvar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(saveButton, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer6, gbc);
        cancelButton = new JButton();
        cancelButton.setText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(cancelButton, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(spacer7, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        mainPanel.add(spacer8, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
