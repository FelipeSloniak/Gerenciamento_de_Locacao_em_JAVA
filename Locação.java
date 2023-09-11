import javax.swing.JOptionPane;
import java.time.LocalDate;

public class Locatario {
    private String NomeLocatario;
    private String CPF;
    private String Telefone;
    private int AnoNascimento;

    public String getNomeLocatario() {
        return NomeLocatario;
    }

    public void setNomeLocatario(String NomeLocatario) {
        this.NomeLocatario = NomeLocatario;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public int getAnoNascimento() {
        return AnoNascimento;
    }

    public void setAnoNascimento(int AnoNascimento) {
        this.AnoNascimento = AnoNascimento;
    }
    public void CadastrarLocatario(){
        this.NomeLocatario = JOptionPane.showInputDialog("Qual o Nome do Locatario: "); 
        this.CPF = JOptionPane.showInputDialog("Qual o CPF do Locatario: ");
        CPF = CPF.replaceAll("[^\\d]", "");
        CPF = CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9);
        this.Telefone = JOptionPane.showInputDialog("Qual o Telefone do Locatario: ");
        Telefone = Telefone.replaceAll("[^\\d]", "");
        Telefone = Telefone.substring(0, 4) + "-" + Telefone.substring(4, 8);
        this.AnoNascimento = Integer.parseInt(JOptionPane.showInputDialog("Qual o ano de nascimento do Locatario: "));
    }
    public boolean VerificarIdade(){
        int anoAtual = LocalDate.now().getYear();
        if ((anoAtual - AnoNascimento) < 18){
            JOptionPane.showMessageDialog(null, "O Locatario é menor de idade!!!");
            return false;
        }
        return true;
    }
}

public class Quadra {
    private String NomeQuadra;
    private String TipoQuadra;
    private int ValorMin;

    public String getNomeQuadra() {
        return NomeQuadra;
    }

    public void setNomeQuadra(String NomeQuadra) {
        this.NomeQuadra = NomeQuadra;
    }

    public String getTipoQuadra() {
        return TipoQuadra;
    }

    public void setTipoQuadra(String TipoQuadra) {
        this.TipoQuadra = TipoQuadra;
    }

    public int getValorMin() {
        return ValorMin;
    }

    public void setValorMin(int ValorMin) {
        this.ValorMin = ValorMin;
    }
    public void CadastrarQuadra(){
        this.NomeQuadra = JOptionPane.showInputDialog("Qual o nome da Quadra: ");
        this.TipoQuadra = JOptionPane.showInputDialog("Qual o tipo da Quadra?:\n1- Quadra de areia.\n2- Quadra de Saibro.");
        int num = Integer.parseInt(this.TipoQuadra);
        switch (num) {
            case 1:
                this.TipoQuadra = "Quadra de areia";
                break;
            case 2:
                this.TipoQuadra = "Quadra de Saibro";
                break;
            default:
                JOptionPane.showMessageDialog(null,"Digite um Valor Valido!!!");
                break;
        }        
        this.ValorMin = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor do minuto da " + this.getNomeQuadra() + ": "));
    }
    
}

public class Locação {
    private Quadra quadra;
    private Locatario locatario;
    private int TempoLocacao;
    private boolean NecessitaEquipamento;
    
    public void CadastroLocação(){
        quadra = new Quadra();
        locatario = new Locatario();
        quadra.CadastrarQuadra();
        locatario.CadastrarLocatario();
        locatario.VerificarIdade();
        if (locatario.VerificarIdade() == false){
            return;
        }
        TempoLocacao = Integer.parseInt(JOptionPane.showInputDialog("Qual o tempo de locação: "));
        NecessitaEquipamento = JOptionPane.showInputDialog("Necessita de Equipamento? (S/N): ").equalsIgnoreCase("S");
    }
    public double calcularValorLocacao(){
        double valorLocacao = quadra.getValorMin() * TempoLocacao;
        if(TempoLocacao >= 120){
            valorLocacao = valorLocacao * 0.9;
        }
        if(NecessitaEquipamento){
            valorLocacao += 50;
        }
        return valorLocacao;
        
    }
    public void Mostrar(){
        double valorLocacao = this.calcularValorLocacao();
        String Resumo = "Locatário\n" +
                "Nome: " + locatario.getNomeLocatario() + "\n" + 
                "CPF: " + locatario.getCPF() + "\n" + 
                "Telefone: " + locatario.getTelefone() + "\n" + 
                "Ano de Nascimento: " + locatario.getAnoNascimento() + "\n" + 
                "Quadra\n" +
                "Nome Quadra: " + quadra.getNomeQuadra() + "\n" +
                "Tipo Quadra: " + quadra.getTipoQuadra() + "\n" +
                "Valor/Min: " + quadra.getValorMin() + " Reais" + "\n" + 
                "Locação\n" + 
                "Tempo de Locação: " + TempoLocacao + " Minutos" + "\n" +
                "Equipamentos: " + NecessitaEquipamento + "\n" + 
                "Valor Total: " + valorLocacao + " Reais";
        JOptionPane.showMessageDialog(null, Resumo);
    }
}

public class MAPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locação l = new Locação();
        l.CadastroLocação();
        l.calcularValorLocacao();
        l.Mostrar();
    }
    
}
