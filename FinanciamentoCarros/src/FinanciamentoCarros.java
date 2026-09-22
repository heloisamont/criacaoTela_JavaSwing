import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class FinanciamentoCarros extends JFrame {

    private JComboBox<String> cbMarca;
    private JTextField txtModelo;
    private JComboBox<Integer> cbAno;
    private JTextField txtValor;

    private JRadioButton rbNovo;
    private JRadioButton rbUsado;

    private JPanel painelUsado;
    private JTextField txtQuilometragem;
    private JTextField txtProprietarios;

    private JCheckBox chkEntrada;
    private JTextField txtEntrada;
    private JComboBox<Integer> cbParcelas;

    private JPanel painelResultado;
    private JLabel lblValorFinanciado;
    private JLabel lblValorParcela;
    private JLabel lblTotalPagar;


    private static final double TAXA = 0.32;

    public FinanciamentoCarros() {
        setTitle("Financiamento de Carros");
        setSize(700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarInterface();
    }

    private void criarInterface() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titulo = new JLabel("Financiamento de Carros", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));

        // DADOS DO VEÍCULO

        JPanel painelVeiculo = new JPanel(new GridBagLayout());
        painelVeiculo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Dados do Veículo",
                TitledBorder.LEFT,
                TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelVeiculo.add(new JLabel("Marca:"), gbc);

        cbMarca = new JComboBox<>(new String[]{
                "FIAT", "Volkswagen", "Chevrolet", "Toyota",
                "Honda", "Hyundai", "Ford"
        });

        gbc.gridx = 1;
        gbc.weightx = 1;
        painelVeiculo.add(cbMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelVeiculo.add(new JLabel("Modelo:"), gbc);

        txtModelo = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelVeiculo.add(txtModelo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        painelVeiculo.add(new JLabel("Ano:"), gbc);

        cbAno = new JComboBox<>();
        for (int ano = 2026; ano >= 2000; ano--) {
            cbAno.addItem(ano);
        }

        gbc.gridx = 1;
        gbc.weightx = 1;
        painelVeiculo.add(cbAno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        painelVeiculo.add(new JLabel("Valor:"), gbc);

        txtValor = new JTextField();
        gbc.gridx = 1;
        gbc.weightx = 1;
        painelVeiculo.add(txtValor, gbc);

        // TIPO

        JPanel painelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTipo.setBorder(BorderFactory.createTitledBorder("Tipo"));

        rbNovo = new JRadioButton("Novo");
        rbUsado = new JRadioButton("Usado");
        rbNovo.setSelected(true);

        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(rbNovo);
        grupoTipo.add(rbUsado);

        painelTipo.add(rbNovo);
        painelTipo.add(rbUsado);

        rbNovo.addActionListener(e -> atualizarPainelUsado());
        rbUsado.addActionListener(e -> atualizarPainelUsado());

        // DADOS DO VEÍCULO USADO

        painelUsado = new JPanel(new GridBagLayout());
        painelUsado.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Dados do Veículo Usado"));

        txtQuilometragem = new JTextField();
        txtProprietarios = new JTextField();

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelUsado.add(new JLabel("Quilometragem:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        painelUsado.add(txtQuilometragem, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelUsado.add(new JLabel("Proprietários:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        painelUsado.add(txtProprietarios, gbc);

        // FINANCIAMENTO

        JPanel painelFinanciamento = new JPanel(new GridBagLayout());
        painelFinanciamento.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Financiamento"));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        chkEntrada = new JCheckBox("Possui entrada?");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelFinanciamento.add(chkEntrada, gbc);

        JLabel lblEntrada = new JLabel("Entrada:");
        txtEntrada = new JTextField();

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painelFinanciamento.add(lblEntrada, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        painelFinanciamento.add(txtEntrada, gbc);

        JLabel lblParcelas = new JLabel("Parcelas:");
        cbParcelas = new JComboBox<>(new Integer[]{12, 24, 36, 48, 60});

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        painelFinanciamento.add(lblParcelas, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        painelFinanciamento.add(cbParcelas, gbc);

        lblEntrada.setVisible(false);
        txtEntrada.setVisible(false);

        chkEntrada.addActionListener(e -> {
            boolean possuiEntrada = chkEntrada.isSelected();
            lblEntrada.setVisible(possuiEntrada);
            txtEntrada.setVisible(possuiEntrada);
            painelFinanciamento.revalidate();
            painelFinanciamento.repaint();
        });

        // BOTÕES

        JPanel painelBotoes = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnCalcular = new JButton("CALCULAR");
        JButton btnLimpar = new JButton("LIMPAR");

        btnCalcular.setPreferredSize(new Dimension(140, 40));
        btnLimpar.setPreferredSize(new Dimension(140, 40));

        painelBotoes.add(btnCalcular);
        painelBotoes.add(btnLimpar);

        btnCalcular.addActionListener(e -> calcular());
        btnLimpar.addActionListener(e -> limpar());

        // RESULTADO

        painelResultado = new JPanel();
        painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
        painelResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));

        lblValorFinanciado = new JLabel("Valor financiado: R$ 0,00");
        lblValorParcela = new JLabel("Valor da parcela: R$ 0,00");
        lblTotalPagar = new JLabel("Total a pagar: R$ 0,00");

        Font fonteResultado = new Font("Arial", Font.BOLD, 16);
        lblValorFinanciado.setFont(fonteResultado);
        lblValorParcela.setFont(fonteResultado);
        lblTotalPagar.setFont(fonteResultado);

        painelResultado.add(lblValorFinanciado);
        painelResultado.add(Box.createVerticalStrut(10));
        painelResultado.add(lblValorParcela);
        painelResultado.add(Box.createVerticalStrut(10));
        painelResultado.add(lblTotalPagar);

        painelResultado.setVisible(false);

        // MONTAGEM

        conteudo.add(painelVeiculo);
        conteudo.add(Box.createVerticalStrut(10));
        conteudo.add(painelTipo);
        conteudo.add(Box.createVerticalStrut(10));
        conteudo.add(painelUsado);
        conteudo.add(Box.createVerticalStrut(10));
        conteudo.add(painelFinanciamento);
        conteudo.add(Box.createVerticalStrut(10));
        conteudo.add(painelBotoes);
        conteudo.add(Box.createVerticalStrut(10));
        conteudo.add(painelResultado);

        JScrollPane scroll = new JScrollPane(conteudo);
        scroll.setBorder(null);
        painelPrincipal.add(scroll, BorderLayout.CENTER);

        add(painelPrincipal);

        atualizarPainelUsado();
    }

    private void atualizarPainelUsado() {
        painelUsado.setVisible(rbUsado.isSelected());
        revalidate();
        repaint();
    }

    private void calcular() {
        if (txtModelo.getText().trim().isEmpty()) {
            mostrarErro("Informe o modelo do veículo.", txtModelo);
            return;
        }

        double valorVeiculo;

        try {
            valorVeiculo = converterValor(txtValor.getText());

            if (valorVeiculo <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            mostrarErro("Informe um valor válido para o veículo.", txtValor);
            return;
        }

        if (rbUsado.isSelected()) {
            if (txtQuilometragem.getText().trim().isEmpty()) {
                mostrarErro("Informe a quilometragem do veículo.", txtQuilometragem);
                return;
            }

            if (txtProprietarios.getText().trim().isEmpty()) {
                mostrarErro("Informe a quantidade de proprietários.", txtProprietarios);
                return;
            }
        }

        double entrada = 0;

        if (chkEntrada.isSelected()) {
            try {
                entrada = converterValor(txtEntrada.getText());

                if (entrada < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                mostrarErro("Informe um valor válido para a entrada.", txtEntrada);
                return;
            }

            if (entrada >= valorVeiculo) {
                mostrarErro("A entrada deve ser menor que o valor do veículo.", txtEntrada);
                return;
            }
        }

        double valorFinanciado = valorVeiculo - entrada;
        int numeroParcelas = (Integer) cbParcelas.getSelectedItem();

        double valorTotal = valorFinanciado * (1 + TAXA);
        double valorParcela = valorTotal / numeroParcelas;

        NumberFormat moeda = NumberFormat.getCurrencyInstance(
                new Locale("pt", "BR"));

        lblValorFinanciado.setText(
                "Valor financiado: " + moeda.format(valorFinanciado));

        lblValorParcela.setText(
                "Valor da parcela: " + moeda.format(valorParcela));

        lblTotalPagar.setText(
                "Total a pagar: " + moeda.format(valorParcela * numeroParcelas));

        painelResultado.setVisible(true);

        revalidate();
        repaint();
    }

    private void mostrarErro(String mensagem, JComponent campo) {
        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        campo.requestFocus();
    }

    private double converterValor(String texto) {
        texto = texto.trim()
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();

        return Double.parseDouble(texto);
    }

    private void limpar() {
        cbMarca.setSelectedIndex(0);
        txtModelo.setText("");
        cbAno.setSelectedIndex(0);
        txtValor.setText("");

        rbNovo.setSelected(true);

        txtQuilometragem.setText("");
        txtProprietarios.setText("");

        chkEntrada.setSelected(false);
        txtEntrada.setText("");

        cbParcelas.setSelectedIndex(0);

        painelResultado.setVisible(false);

        atualizarPainelUsado();

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinanciamentoCarros tela = new FinanciamentoCarros();
            tela.setVisible(true);
        });
    }
}
