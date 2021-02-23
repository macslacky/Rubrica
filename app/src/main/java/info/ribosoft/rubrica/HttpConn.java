package info.ribosoft.rubrica;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class HttpConn {
    private RequestQueue myRequest;
    public class recContact {
        String firstName, Surname, phone;
    }

    public interface vollCallback {
        void onSuccess(String response);
        void onError(String error);
    }

    public void postStringRequest(final Context context, String url, String selMenu,
        String idContatto, final vollCallback callback) {
        // set on requestqueue, context allows access to application specific classes and resources
        myRequest = Volley.newRequestQueue(context);
        //specifies a url and receives a string
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // the request was successful
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // the request generated an error
                callback.onError(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sel_menu", selMenu);
                params.put("id_contact", idContatto);
                return params;
            }
        };
        // send a requestqueue
        myRequest.add(postRequest);
    }

    public void postInsertRequest(final Context context, String url, String selMenu,
        String firstName, String surname, String phone, final vollCallback callback) {
        // set on requestqueue, context allows access to application specific classes and resources
        myRequest = Volley.newRequestQueue(context);
        //specifies a url and receives a string
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // the request was successful
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // the request generated an error
                callback.onError(error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sel_menu", selMenu);
                params.put("firstname", firstName);
                params.put("surname", surname);
                params.put("phone", phone);
                return params;
            }
        };
        // send a requestqueue
        myRequest.add(postRequest);
    }

    public void postUpdateRequest(final Context context, String url, String selMenu,
        String idContact, String firstName, String surname, String phone,
        final vollCallback callback) {
        // set on requestqueue, context allows access to application specific classes and resources
        myRequest = Volley.newRequestQueue(context);
        //specifies a url and receives a string
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // the request was successful
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // the request generated an error
                callback.onError(error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sel_menu", selMenu);
                params.put("id_contact", idContact);
                params.put("firstname", firstName);
                params.put("surname", surname);
                params.put("phone", phone);
                return params;
            }
        };
        // send a requestqueue
        myRequest.add(postRequest);
    }

    public void  postDeleteRequest(final Context context, String url, String selMenu,
        String idContact, final vollCallback vollCallback) {
        // set on requestqueue, context allows access to application specific classes and resources
        myRequest = Volley.newRequestQueue(context);
        //specifies a url and receives a string
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // the request was successful
                vollCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // the request generated an error
                vollCallback.onError(error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sel_menu", selMenu);
                params.put("id_contact", idContact);
                return params;
            }
        };
        // send a requestqueue
        myRequest.add(postRequest);
    }

    public void postStringRequest(final Context context, String url, String selMenu,
        final vollCallback callback) {
        // set on requestqueue, context allows access to application specific classes and resources
        myRequest = Volley.newRequestQueue(context);
        // specifies a url and receives a string
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // the request was successful
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // the request generated an error
                callback.onError(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sel_menu", selMenu);
                return params;
            }
        };
        // send a requestqueue
        myRequest.add(getRequest);
    }
}
