import javax.swing.SwingWorker;
import javax.swing.JLabel;
import java.util.concurrent.ExecutionException;

public class BackgroundCalculator extends SwingWorker<Long, Object>
{
    private final int n; // Número de Fibonacci a calcular
    private final JLabel resultJLabel; // JLabel para exibir o resultado
    private long nthFib;
    
    // construtor
    public BackgroundCalculator(int n, JLabel resultJLabel)
    {
        this.n = n;
        this.resultJLabel = resultJLabel;
    }
    
    // código de longa duração para ser executado em uma thread trabalhadora
    public Long doInBackground()
    {
        return nthFib = fibonacci(n);
    }
    
    // código a ser executado na thread de despacho de eventos quando doInBackground retorna
    protected void done()
    {
        try
        {
            // obtém o resultado de doInBackground e o exibe
            resultJLabel.setText(get().toString()); 
        }
        catch (InterruptedException ex)
        {
            resultJLabel.setText("Interrupted while waiting for results.");
        }
        catch (ExecutionException ex)
        {
            resultJLabel.setText("Error encountered while performing calculation.");
        }
    }
    
    // método fibonacci recursivo; calcula o enésimo número de Fibonacci
    public long fibonacci(long number)
    {
        if (number == 0 || number == 1)
            return number;
        else
            return fibonacci(number - 1) + fibonacci(number - 2);
    }
} // fim da classe BackgroundCalculator