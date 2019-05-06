package ro.msg.learning.shop.mapping;

import org.modelmapper.ModelMapper;

public abstract class Mapper<E1, E2> {
    public final ModelMapper modelmapper = new ModelMapper();
    public abstract E1 convertToEntity(E2 dto);

    public abstract E2 convertToDto(E1 entity);
}
