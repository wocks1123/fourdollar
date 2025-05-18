package org.example.fourdollar.tag.application.port.out;

import org.example.fourdollar.tag.domain.Tag;

import java.util.List;

public interface LoadTagListPort {

    List<Tag> findAll();

}
