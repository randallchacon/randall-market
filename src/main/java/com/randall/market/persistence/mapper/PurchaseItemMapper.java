package com.randall.market.persistence.mapper;

import com.randall.market.domain.PurchaseItem;
import com.randall.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}) //se necesita agregar el uses aunque sea para ignorar la clase
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target="productId"),
            @Mapping(source = "cantidad", target="quantity"),
            //@Mapping(source = "total", target="total"), //no es necesario agregarlo pues los dos se llaman igual
            @Mapping(source = "estado", target="active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
