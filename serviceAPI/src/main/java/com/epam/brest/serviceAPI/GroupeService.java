package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.Groupe;

import java.util.List;

public interface GroupeService {
    List<Groupe> getAllGroupesByNameService();
}
