package src.utils.objects;

/**
 * Содержит параметры которые могут использоваться при конфигурации.
 * */
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
    link,
    title;

    private String type;

    Params(){};
    Params (String type){
        this.type = type;
    }
}