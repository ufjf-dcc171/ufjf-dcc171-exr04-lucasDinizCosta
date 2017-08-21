package aula04exr03;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Janela extends JFrame {

    /**
     * Exercício 4) Construa uma aplicação gráfica em Java que realize o Jogo da Forca.
     * Sugestão:
     * -> A palavra secreta deve ficar em um JPasswordField 
     * -> O jogador deve entrar com uma letra por vez e perde se errar 5 vezes. 
     * -> Uma etiqueta deve mostrar os acertos atuais, por exemplo:      
     *      ???????
     *      ?E??E??
     *      ?EC?E??
     *      ?EC?ET?
     * 
     */
    
    private String palavra;
    private JTextField caixa_Letra;
    private int chances = 5;    //Se chegar a 0 ele perde
    private JLabel escrito_Chances = new JLabel("Chances: " + chances + "        ");
    private JLabel escrito_1 = new JLabel("<HTML>Digite um palavra e aperte<br>ENTER pra registrá-la: </HTML>");
    private JLabel escrito_2 = new JLabel("Digite uma letra: ");
    private JPasswordField caixa_Palavra;
    private JButton botao_Inserir = new JButton("Inserir");
    private JLabel escrito_Resultado = new JLabel("Resultado: ");
    private JLabel resultado = new JLabel();

    public Janela() throws HeadlessException {
        super("Jogo da Forca");
        setLayout(new FlowLayout());
        caixa_Letra = new JTextField(20);
        caixa_Palavra = new JPasswordField(20);
        botao_Inserir.addActionListener(new funcao());
        caixa_Palavra.addActionListener(new funcao());
        add(escrito_1);
        add(caixa_Palavra);
        add(escrito_2);
        add(caixa_Letra);
        add(botao_Inserir);
        add(escrito_Chances);
        add(escrito_Resultado);
        add(resultado);
    }

    private class funcao implements ActionListener {

        public funcao() {
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botao_Inserir) {
                if (caixa_Letra.getText().length() > 1 && caixa_Palavra.getText() != "") {
                    JOptionPane.showMessageDialog(null, "Digite apenas uma letra", "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (caixa_Letra.getText().length() == 1 && caixa_Palavra.getText() != "") {
                    if (palavra.contains(caixa_Letra.getText())) {     //Se contem o caractere digitado
                        for (int i = 0; i < palavra.length(); i++) {
                            if (caixa_Letra.getText().equals(palavra.charAt(i) + "")) {  //Uma das posições que o caractere se encontra
                                StringBuilder sb = new StringBuilder(resultado.getText());          //Trocar o caractere de uma determinada posição em uma String
                                sb.setCharAt(i, palavra.charAt(i));
                                resultado.setText(sb.toString());
                            }
                        }
                        if (resultado.getText().contains("?") == false) {                       //Se descobriu todos os caracteres
                            JOptionPane.showMessageDialog(null, "Você ganhou !!!", "Vitoria", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }
                    } else {
                        if (chances != 0) {
                            chances--;
                            JOptionPane.showMessageDialog(null, "O caractere digitado nao esta presente na palavra.\n Você só tem mais " + chances + " chances, fique atento.", "Erro", JOptionPane.INFORMATION_MESSAGE);
                            escrito_Chances.setText("Chances: " + chances + "        ");
                        } else {
                            JOptionPane.showMessageDialog(null, "Limite de tentativas ultrapassado. A palavra era: " + palavra, "Erro", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }

                    }
                }
            }
            if (e.getSource() == caixa_Palavra) {
                JOptionPane.showMessageDialog(null, "TESTE");
                palavra = caixa_Palavra.getText();
                resultado.setText("");   //Limpando o texto
                for (int i = 0; i < palavra.length(); i++) {
                    resultado.setText(resultado.getText() + "?");
                }
            }
        }
    }
}
