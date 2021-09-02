package com.metastring.kew.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ActivateRequestContext
@ApplicationScoped
@Transactional


@Table(name = "common_names", uniqueConstraints = @UniqueConstraint(columnNames = {}))
public class CommonNames extends PanacheEntityBase  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @Column(name = "plant_id")
    public Long plantId;

    @Column(name = "language_code")
    public String languageCode;
    @Column(name = "common_name")
    public String commonName;
    @Column(name = "refId")
    public Long refId;



    public static List<CommonNames> findByPlantId(Long id) {
        return list("plantId", id);
    }

    public static List<CommonNames> findByString(String name) {
        return list("common_name", name);
    }


    public List<CommonNames> listWhereLike(String s)  {
        return list("common_name like ?1",  "%"+s+"%");
    }

    public List<Long> listOfPlantIdWhereCommonNameLike(String s){
        List<Long> l=new ArrayList<>();
        List<CommonNames> commonNamesList=listWhereLike(s);
        for (CommonNames c:commonNamesList ) {

            l.add(c.getPlantId());
        }
        return l;
    }

    public List<String> listOfCommonNamesByPlantId(Long l){
        List<String> stringList=new ArrayList<>();

        List<CommonNames> cnl=CommonNames.findByPlantId(l);

        for (CommonNames cn:cnl) {
            stringList.add(cn.getCommonName());
        }

    return stringList;
    }


}
