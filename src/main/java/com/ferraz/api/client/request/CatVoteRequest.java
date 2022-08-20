package com.ferraz.api.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatVoteRequest {

    @JsonProperty("image_id")
    public String image_id;

    @JsonProperty("value")
    public boolean value;

    @JsonProperty("sub_id")
    public String sub_id;
}
