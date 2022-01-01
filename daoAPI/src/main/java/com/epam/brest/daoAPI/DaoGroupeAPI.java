package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Groupe;

import java.util.List;

public interface DaoGroupeAPI {

    List<Groupe> getGroupesByName();
}
