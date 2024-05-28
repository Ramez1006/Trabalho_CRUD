// CarroDAO.java
import java.io.*;
import java.util.*;

public class CarroDAO {
    private static final String FILE_NAME = "carros.txt";
    private List<Carro> carros = new ArrayList<>();

    public CarroDAO() {
        carregarCarros();
    }

    public void adicionarCarro(Carro carro) {
        carros.add(carro);
        salvarCarros();
    }

    public List<Carro> listarCarros() {
        return carros;
    }

    public Carro buscarCarro(int id) {
        for (Carro carro : carros) {
            if (carro.getId() == id) {
                return carro;
            }
        }
        return null;
    }

    public void atualizarCarro(Carro carroAtualizado) {
        Carro carro = buscarCarro(carroAtualizado.getId());
        if (carro != null) {
            carro.setNome(carroAtualizado.getNome());
            carro.setPreco(carroAtualizado.getPreco());
            salvarCarros();
        }
    }

    public void removerCarro(int id) {
        Carro carro = buscarCarro(id);
        if (carro != null) {
            carros.remove(carro);
            salvarCarros();
        }
    }

    private void carregarCarros() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                carros.add(Carro.fromString(linha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar carros: " + e.getMessage());
        }
    }

    private void salvarCarros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Carro carro : carros) {
                writer.write(carro.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar carros: " + e.getMessage());
        }
    }
}
