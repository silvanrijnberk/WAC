package nl.hu.v1wac.webservices;

import nl.hu.v1wac.model.Country;
import nl.hu.v1wac.model.ServiceProvider;
import nl.hu.v1wac.model.WorldService;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("countries")
public class WorldRecource{

    private JsonObject convertToJson(Country country) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("code", country.getCode());
        jsonObjectBuilder.add("iso3", country.getIso3());
        jsonObjectBuilder.add("name", country.getName());
        jsonObjectBuilder.add("capital", country.getCapital());
        jsonObjectBuilder.add("continent", country.getContinent());
        jsonObjectBuilder.add("region", country.getRegion());
        jsonObjectBuilder.add("surface", country.getSurface());
        jsonObjectBuilder.add("population", country.getPopulation());
        jsonObjectBuilder.add("government", country.getGovernment());
        jsonObjectBuilder.add("latitude", country.getLatitude());
        jsonObjectBuilder.add("longitude", country.getLongitude());

        return jsonObjectBuilder.build();
    }


    @GET
    @Path("/")
    public String listCountries() {
        WorldService worldService = ServiceProvider.getWorldService();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Country country : worldService.getAllCountries()) {
            JsonObject countryJson = convertToJson(country);
            jsonArrayBuilder.add(countryJson);
        }

        JsonArray array = jsonArrayBuilder.build();

        return array.toString();
    }

    @GET
    @Path("/{code}")
    public String getCountryById(@PathParam("code") String code) {
        WorldService worldService = ServiceProvider.getWorldService();

        Country country = worldService.getCountryByCode(code);
        JsonObject jsonCountry = convertToJson(country);

        return jsonCountry.toString();
    }

    @GET
    @Path("/largestsurfaces")
    public String getLargestSurfaces() {
        WorldService ws = ServiceProvider.getWorldService();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Country c : ws.get10LargestSurfaces()) {
            JsonObject countryJson = convertToJson(c);
            jsonArrayBuilder.add(countryJson);
        }

        JsonArray array = jsonArrayBuilder.build();
        return array.toString();
    }


    @GET
    @Path("/largestpopulations")
    public String getLargestPopulations() {
        WorldService worldServices = ServiceProvider.getWorldService();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Country country : worldServices.get10LargestPopulations()) {
            JsonObject countryJson = convertToJson(country);
            jsonArrayBuilder.add(countryJson);
        }

        JsonArray array = jsonArrayBuilder.build();
        return array.toString();
    }
}
