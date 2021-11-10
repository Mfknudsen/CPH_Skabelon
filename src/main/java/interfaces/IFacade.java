package interfaces;

import java.util.List;

//The Generic Type <T> Is The Intended DTO That The Facade Handles
public interface IFacade <T> {
    T create(T classDTO);
    T getById(long id);
    long getCount();
    List<T> getAll();
    List<T> getSpecific(String valueType ,String value);
}
