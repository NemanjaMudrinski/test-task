package cyrillic_software.models;

import java.io.Serializable;

public interface Indetification<ID> extends Serializable {

    public ID getId();

    public void setId(ID id);

}