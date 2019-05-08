package ro.msg.learning.shop.mapping;

public interface Mapper<E1, E2> {
    E1 convertToEntity(E2 dto);

    E2 convertToDto(E1 entity);
}
