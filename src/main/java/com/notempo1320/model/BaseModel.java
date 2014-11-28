package com.notempo1320.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

public abstract class BaseModel {
     public UUID randomUUID() {
        return UUID.randomUUID();
    }


}
