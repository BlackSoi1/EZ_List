package persistence;

import org.json.JSONObject;

// model code base on JsonSerializationDemo-Writable
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
