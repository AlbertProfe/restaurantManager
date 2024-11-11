public OrderMenuQty updateOrderMenuQty(String id, OrderMenuQty OrderMenuQtyDetails){
        OrderMenuQty orderMenuQty = orderMenuQtyRepository.findById(id).orElse(null);
        assert orderMenuQty != null;
        orderMenuQty.setQuantity(OrderMenuQtyDetails.getQuantity());
        orderMenuQty.setOrderMapped(OrderMenuQtyDetails.getOrderMapped());
        orderMenuQty.setMenuMapped(OrderMenuQtyDetails.getMenuMapped());

        return orderMenuQtyRepository.save(orderMenuQty);

}