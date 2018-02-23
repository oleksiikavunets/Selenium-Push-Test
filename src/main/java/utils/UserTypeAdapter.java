package utils;

import com.google.gson.*;
import com.selenium.pojo.User;

import java.lang.reflect.Type;

public class UserTypeAdapter implements JsonDeserializer<User>/*, JsonSerializer<User>*/ {
	

	private Gson defaultGson;

	public UserTypeAdapter(Gson defaultGson) {
		this.defaultGson = defaultGson;
	}

	@Override
	public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        User user = defaultGson.fromJson(json, typeOfT);
        return user;
		}

//	@Override
//	public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
//
//		JsonObject userJson = new JsonObject();
//		userJson.addProperty("id",user.getId());
//		userJson.addProperty("m",user.getMerchantId());
//		userJson.addProperty("l",user.getLogin());
//		userJson.addProperty("e",user.getEmail());
//		userJson.addProperty("s",user.getSkype());
//		userJson.addProperty("pos",user.getPosition());
//		userJson.addProperty("r",user.getRole());
//		userJson.addProperty("n",user.getName());
//		userJson.addProperty("ph",user.getPhone());
//		userJson.addProperty("d",user.isDisabled());
//		userJson.addProperty("f",user.isFrozen());
//		if (user.getTarifExpirityDate() != null) {
//		userJson.addProperty("ed",user.getTarifExpirityDate().toString());
//		}
//		userJson.addProperty("t", user.getTariff().toString());
//		return userJson;
//	}
	
}
