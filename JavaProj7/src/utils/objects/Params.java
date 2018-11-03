package src.utils.objects;

public enum Params {
    id,
    height,
    width,
    map,
    id_in_seq,
    sprite_solo_file_type,
    seq_max_val,
    image,
    typeObject,
    binded_object,
    type_prev,
    type_nexts,
    type_next,
    link;

    private String type;

    Params(){};
    Params (String type){
        this.type = type;
    }
}