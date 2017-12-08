package view;

import controller.AuthController;
import model.CounterItem;
import model.Exchange;
import model.Product;
import model.User;
import util.ArrayListModel;
import util.MyFrame;
import util.MyFrameEditor;
import util.StringCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class ExchangeView extends MyFrameEditor<Exchange> {

    private JPanel mainPanel;
    private JLabel user1Label;
    private JButton addProductButton1;
    private JButton removeProductButton1;
    private JList<Product> productList1;
    private JLabel user2Label;
    private JButton addProductButton2;
    private JButton removeProductButton2;
    private JList<Product> productList2;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton acceptButton1;
    private JButton concludeButton1;
    private JButton acceptButton2;
    private JButton concludeButton2;
    private JButton modifyButton;


    private ProductSelector productSelector;
    private ArrayListModel<Product> productModel1 = new ArrayListModel<>(new ArrayList<>());
    private ArrayListModel<Product> productModel2 = new ArrayListModel<>(new ArrayList<>());

    public ExchangeView() {
        super();

        setTitle("");
        setSize(600, 450);
        setContentPane(mainPanel);

        cancelButton.addActionListener(e -> {
            close();
        });

        productSelector = (ProductSelector) ViewBus.get().get(ProductSelector.class);

        setupSide(productModel1, productList1, addProductButton1, removeProductButton1, acceptButton1, 1);
        setupSide(productModel2, productList2, addProductButton2, removeProductButton2, acceptButton2, 2);
    }

    private void setupSide(
            final ArrayListModel<Product> productModel,
            JList<Product> productList,
            JButton addProductButton,
            JButton removeProductButton,
            JButton acceptButton,
            int index) {

        productList.setModel(productModel);
        productList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        productList.setCellRenderer(new StringCellRenderer<Product>(x -> x.getName()));

        productList.addListSelectionListener(e -> {
            Product p = productList.getSelectedValue();
            if (p != null) {
                removeProductButton.setEnabled(true);
            } else {
                removeProductButton.setEnabled(false);
            }
        });


        addProductButton.addActionListener(e -> {
            productSelector.open(Product.store.List(x -> x.getUserId() == getUser(index).getId()));

            productSelector.onSelect.addListener(list -> {
                productModel.add(list);
            });
        });

        ///// Remove
        removeProductButton.addActionListener(e -> {
            List<Product> list = productList.getSelectedValuesList();

            productModel.remove(list);
        });


        //// Accept
        acceptButton.addActionListener(e -> {
            accept(index);
        });
    }

    private User getUser(int index) {
        if (index == 1) {
            return current.getUser1();
        } else {
            return current.getUser2();
        }
    }

    @Override
    protected Exchange create() {
        return new Exchange();
    }

    @Override
    protected void save() {
        Exchange.store.Save(current);
    }

    @Override
    protected void readForm() {
        current.getUser1Products().setList(productModel1.getList().stream().map(x -> x.getId()).collect(Collectors.toList()));
        current.getUser2Products().setList(productModel2.getList().stream().map(x -> x.getId()).collect(Collectors.toList()));
    }

    @Override
    protected void fillForm() {
        user1Label.setText(getuserLabel(current.getUser1(), current.isUser1Accepted(), current.isUser1Concluded()));
        user2Label.setText(getuserLabel(current.getUser2(), current.isUser2Accepted(), current.isUser2Concluded()));

        productModel1.setList(current.getUser1Products().getModels());
        productModel2.setList(current.getUser2Products().getModels());

        removeProductButton1.setEnabled(false);
        removeProductButton2.setEnabled(false);

        // Edit
        if (!canEdit()) {
            productList1.setEnabled(false);
            addProductButton1.setEnabled(false);

            productList2.setEnabled(false);
            addProductButton2.setEnabled(false);

            saveButton.setEnabled(false);
            modifyButton.setEnabled(!canConclude());
        } else {
            productList1.setEnabled(true);
            addProductButton1.setEnabled(true);

            productList2.setEnabled(true);
            addProductButton2.setEnabled(true);

            saveButton.setEnabled(true);
            modifyButton.setEnabled(false);
        }


        /// Accept
        if (isUser(1) && !current.isUser1Accepted()) {
            acceptButton1.setEnabled(true);
        } else {
            acceptButton1.setEnabled(false);
        }

        if (isUser(2) && !current.isUser2Accepted()) {
            acceptButton2.setEnabled(true);
        } else {
            acceptButton2.setEnabled(false);
        }


        /// Conclude
        if (canConclude() && isUser(1) && !current.isUser1Concluded()) {
            concludeButton1.setEnabled(true);
        } else {
            concludeButton1.setEnabled(false);
        }

        if (canConclude() && isUser(2) && !current.isUser2Concluded()) {
            concludeButton2.setEnabled(true);
        } else {
            concludeButton2.setEnabled(false);
        }
    }

    private boolean isUser(int index) {
        if (index == 1) {
            return current.getUser1() == AuthController.getCurrentUser();
        } else {
            return current.getUser2() == AuthController.getCurrentUser();
        }
    }

    private boolean canEdit() {
        return !current.isUser1Accepted() && !current.isUser2Accepted() &&
                !current.isUser1Concluded() && !current.isUser2Concluded() &&
                !current.isCanceled();
    }

    private boolean canConclude() {
        return current.isUser1Accepted() && current.isUser2Accepted();
    }

    private String getuserLabel(User user, boolean accepted, boolean concluded) {
        String r = user.getName();

        if (!accepted) {
            r += " - Pendente";
        } else {
            if (!concluded) {
                r += " - Aceito";
            } else {
                r += " - Concluído";
            }
        }

        return r;
    }

    private void accept(int index) {
        boolean confirm = showConfirm("Isso irá salvar quaisquer alterações, prosseguir?");
        if (!confirm) return;

        readForm();

        if (index == 1) {
            current.setUser1Accepted(true);
        } else {
            current.setUser2Accepted(true);
        }

        save();
        fillForm();
    }

    private void modify() {
        boolean confirm = showConfirm("Isso irá resetar os aceites, prosseguir?");
        if (!confirm) return;

        current.setUser1Accepted(false);
        current.setUser2Accepted(false);

        fillForm();
        save();
    }

    private static boolean showConfirm(String message) {
        return showConfirmDialog(null, message, "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
    }

    protected String getModelName() {
        return "Troca";
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
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel1, gbc);
        user1Label = new JLabel();
        user1Label.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel1.add(user1Label, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        productList1 = new JList();
        scrollPane1.setViewportView(productList1);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer2, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel2, gbc);
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
        addProductButton1 = new JButton();
        addProductButton1.setText("Adicionar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(addProductButton1, gbc);
        removeProductButton1 = new JButton();
        removeProductButton1.setText("Remover");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(removeProductButton1, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer5, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel3, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer7, gbc);
        acceptButton1 = new JButton();
        acceptButton1.setText("Aceitar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(acceptButton1, gbc);
        concludeButton1 = new JButton();
        concludeButton1.setText("Concluir");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(concludeButton1, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(spacer8, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel4, gbc);
        user2Label = new JLabel();
        user2Label.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel4.add(user2Label, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel4.add(spacer9, gbc);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(scrollPane2, gbc);
        productList2 = new JList();
        scrollPane2.setViewportView(productList2);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel4.add(spacer10, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(panel5, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(spacer12, gbc);
        addProductButton2 = new JButton();
        addProductButton2.setText("Adicionar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(addProductButton2, gbc);
        removeProductButton2 = new JButton();
        removeProductButton2.setText("Remover");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(removeProductButton2, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel4.add(spacer13, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(panel6, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(spacer14, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(spacer15, gbc);
        acceptButton2 = new JButton();
        acceptButton2.setText("Aceitar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(acceptButton2, gbc);
        concludeButton2 = new JButton();
        concludeButton2.setText("Concluir");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(concludeButton2, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        mainPanel.add(spacer16, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 20;
        mainPanel.add(spacer17, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        mainPanel.add(spacer18, gbc);
        final JPanel spacer19 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        mainPanel.add(spacer19, gbc);
        final JPanel spacer20 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer20, gbc);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel7, gbc);
        saveButton = new JButton();
        saveButton.setText("Salvar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel7.add(saveButton, gbc);
        final JPanel spacer21 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel7.add(spacer21, gbc);
        cancelButton = new JButton();
        cancelButton.setText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel7.add(cancelButton, gbc);
        final JPanel spacer22 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel7.add(spacer22, gbc);
        final JPanel spacer23 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel7.add(spacer23, gbc);
        modifyButton = new JButton();
        modifyButton.setEnabled(false);
        modifyButton.setText("Modificar");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel7.add(modifyButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
