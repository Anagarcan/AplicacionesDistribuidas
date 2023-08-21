package ServidorBanco;

import java.io.*;
import java.util.ArrayList;

public class BaseDatosBanco {

    private final String nombreBaseDeDatos = "db.csv";
    private FileReader fR;
    private BufferedReader bR;
    private FileWriter fW;
    private PrintWriter pR;

    public BaseDatosBanco() throws IOException {
        File file = new File(nombreBaseDeDatos);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void actualizarCuentas(ArrayList<CuentaBancaria> cuentas) {

        try {
            fW = new FileWriter(nombreBaseDeDatos);
            pR = new PrintWriter(fW);

            for (CuentaBancaria cuenta : cuentas) {
                String linea = cuentaAString(cuenta);
                pR.println(linea);
            }

            pR.close();
            fW.close();
        } catch (IOException e) {
            System.out.println("Error I/O" + e);
        }

    }

    public ArrayList<CuentaBancaria> getCuentas() {

        ArrayList <CuentaBancaria> cuentas = new ArrayList<>();

        try {
            fR = new FileReader(nombreBaseDeDatos);
            bR = new BufferedReader(fR);
            String linea = bR.readLine();

            while (linea != null) {
                CuentaBancaria cuenta = stringACuenta(linea);
                cuentas.add(cuenta);
                linea = bR.readLine();
            }

            bR.close();
            fR.close();
        } catch (IOException e) {
            System.out.println("Error I/O" + e);
        }
        return cuentas;
    }

    public CuentaBancaria stringACuenta(String cuenta) {
        String[] trozos = cuenta.split("[;]+");
        String titular = trozos[0];
        String ncuenta = trozos[1];
        double importe = Double.parseDouble(trozos[2]);

        CuentaBancaria cuentaBancaria = new CuentaBancaria(titular, ncuenta, importe);
        return cuentaBancaria;
    }

    public String cuentaAString(CuentaBancaria cuenta) {
        return cuenta.getTitular() + ";" + cuenta.getNumeroCuenta() + ";" + cuenta.getSaldo();

    }

}
