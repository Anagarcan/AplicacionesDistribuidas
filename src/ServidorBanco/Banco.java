package ServidorBanco;

import java.io.IOException;
import java.util.ArrayList;

public class Banco {

    ArrayList<CuentaBancaria> cuentas;
    private BaseDatosBanco baseDatosBanco;

    public Banco() throws IOException {
        baseDatosBanco = new BaseDatosBanco();
        cuentas = this.baseDatosBanco.getCuentas();

    }

    public void crearCuenta(String titular, String ncuenta) throws Exception {
        if (getCuentaBancaria(titular) != null) {
            throw new Exception("ya hay una cuenta con ese titular");
        }
        CuentaBancaria cuentaBancaria = new CuentaBancaria(titular, ncuenta, 0);
        cuentas.add(cuentaBancaria);
        baseDatosBanco.actualizarCuentas(cuentas);
    }

    public CuentaBancaria getCuentaBancaria(String titular) {
        for (int i = 0; i <= cuentas.size() - 1; i++) {
            CuentaBancaria cuenta = cuentas.get(i);
            if (titular.equals(cuenta.getTitular())) {
                return cuenta;
            }
        }
        return null;

    }

    public boolean hacerIngreso(String titular, double importe) throws Exception {
        CuentaBancaria cuenta = getCuentaBancaria(titular);
        if (cuenta == null) {
            throw new Exception("No se encuenta la cuenta bancaria");
        }
        if (importe <= 0) {
            throw new Exception("El importe no puede ser menos de 0€");
        }

        cuenta.setSaldo(cuenta.getSaldo() + importe);
        this.baseDatosBanco.actualizarCuentas(cuentas);
        return true;
    }

    public void retirar(String titular, double importe) throws Exception {
        CuentaBancaria cuenta = getCuentaBancaria(titular);
        if (cuenta == null) {
            throw new Exception("No se encuenta la cuenta bancaria");
        }
        if (importe <= 0) {
            throw new Exception("El importe no puede ser menos de 0€");
        }
        if (importe > cuenta.getSaldo()) {
            throw new Exception("No hay suficiente saldo");
        }
        cuenta.setSaldo(cuenta.getSaldo() - importe);
        this.baseDatosBanco.actualizarCuentas(cuentas);

    }

    public boolean hacerTransferencia(String titularOrigen, String titularDestino, double importe) throws Exception {
        CuentaBancaria cuentaOrigen = getCuentaBancaria(titularOrigen);
        CuentaBancaria cuentaDestino = getCuentaBancaria(titularDestino);

        if (cuentaOrigen == null || cuentaDestino == null) {
            throw new Exception("Alguna de las cuentas no existe");
        }
        if (importe <= 0) {
            throw new Exception("El importe no puede ser menos de 0€");
        }
        if (importe > cuentaOrigen.getSaldo()) {
            throw new Exception("No hay suficiente saldo");
        }
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - importe);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + importe);
        this.baseDatosBanco.actualizarCuentas(cuentas);

        return true;
    }

}
