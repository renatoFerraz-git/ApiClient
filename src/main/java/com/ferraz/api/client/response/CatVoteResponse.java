package com.ferraz.api.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatVoteResponse {

    @JsonProperty("message")
    public String message;

    @JsonProperty("status")
    public int status;

    @JsonProperty("level")
    public String level;

}
