package com.github.marcoscoutozup.ecommerce.compra.pagamento;

import java.util.UUID;

public class Paypal implements Pagamento{

    @Override
    public String retornarUrlDePagamento(UUID id) {
        return "paypal.com/" + id + "?redirectUrl={urlRetornoAppPosPagamento}";
    }
}
