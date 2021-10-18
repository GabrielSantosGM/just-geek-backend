//package br.com.justgeek.mobile.api.legada;
//
//import br.com.justgeek.adapter.carrinho.ItemPedido;
//import br.com.justgeek.mobile.domain.Carrinho;
//import com.mercadopago.MercadoPago;
//import com.mercadopago.exceptions.MPException;
//import com.mercadopago.resources.Preference;
//import com.mercadopago.resources.datastructures.preference.Item;
//
//public class Pagamento {
//
//    public String postItem(ItemPedido itemPedido) throws MPException {
//
//        MercadoPago.SDK.setAccessToken("APP_USR-5136519833053424-051814-327cdb16cd7756d56783b3702447eda6-571029932");
//
//        Preference preference1 = new Preference();
//
//        Item item = new Item();
//        item.setTitle(itemPedido.getTituloItem())
//                .setQuantity(itemPedido.getQuantidadeItem())
//                .setUnitPrice(itemPedido.getPrecoItem().floatValue());
//
//        preference1.appendItem(item);
//        preference1.save();
//        System.out.println(item);
//
////        BackUrls backUrls = new BackUrls(
////                "http://localhost:3000",
////                "http://localhost:3000/pending",
////                "http://localhost:3000/failure");
//
//
////        preference1.setBackUrls(backUrls);
//
//        return preference1.getInitPoint();
//    }
//}
