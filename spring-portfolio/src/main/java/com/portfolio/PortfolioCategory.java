package com.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.text.Normalizer;

import org.json.JSONObject;

@Entity
public class PortfolioCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private static final int MAX_SLUG_LENGTH = 256;
    private String name;
    private String slug;
    private String googleIcon;

    public PortfolioCategory(String name, String googleIcon) {
        super();
        this.name = name;
        this.slug = this.slugify(name);
        this.googleIcon = googleIcon;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
	jsonObject.put("name", this.name);
	jsonObject.put("slug", this.slug);
	jsonObject.put("googleIcon", this.googleIcon);
        return jsonObject;
    }

    public String slugify(String input) {
        final String intermediateResult = Normalizer
            .normalize(input, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "")
            .replaceAll("[^-_a-zA-Z0-9]", "-").replaceAll("\\s+", "-")
            .replaceAll("[-]+", "-").replaceAll("^-", "")
            .replaceAll("-$", "").toLowerCase();
            return intermediateResult.substring(0,
                Math.min(MAX_SLUG_LENGTH, intermediateResult.length()));
    }
}