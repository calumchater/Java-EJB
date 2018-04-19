package ejb.sessions;

import java.io.Serializable;

import javax.ejb.Remote;
@Remote
public interface ServiceInitRemote extends ServiceInit, Serializable {

}
