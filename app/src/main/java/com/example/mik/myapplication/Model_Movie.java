package com.example.mik.myapplication;

import java.io.Serializable;

public class Model_Movie implements Serializable
{
    // Short Info
    private String f_string_title;
    private String f_string_year;
    private String f_string_poster;
    private String f_string_type;

    public String getString_type()
    {
        return f_string_type;
    }

    public void setString_type(String p_string_type)
    {
        f_string_type = p_string_type;
    }

    public String getString_poster()
    {
        return f_string_poster;
    }

    public void setString_poster(String p_string_poster)
    {
        f_string_poster = p_string_poster;
    }

    // Full Info
    private String f_string_rated;
    private String f_string_released;
    private String f_string_runtime;
    private String f_string_genre;
    private String f_string_director;
    private String f_string_writer;
    private String f_string_plot;
    private String f_string_country;
    private String f_string_imdbID;

    public String getString_imdbID()
    {
        return f_string_imdbID;
    }

    public void setString_imdbID(String p_string_imdbID)
    {
        f_string_imdbID = p_string_imdbID;
    }

    public String getString_rated()
    {
        return f_string_rated;
    }

    public void setString_rated(String p_string_rated)
    {
        f_string_rated = p_string_rated;
    }

    public String getString_released()
    {
        return f_string_released;
    }

    public void setString_released(String p_string_released)
    {
        f_string_released = p_string_released;
    }

    public String getString_runtime()
    {
        return f_string_runtime;
    }

    public void setString_runtime(String p_string_runtime)
    {
        f_string_runtime = p_string_runtime;
    }

    public String getString_genre()
    {
        return f_string_genre;
    }

    public void setString_genre(String p_string_genre)
    {
        f_string_genre = p_string_genre;
    }

    public String getString_director()
    {
        return f_string_director;
    }

    public void setString_director(String p_string_director)
    {
        f_string_director = p_string_director;
    }

    public String getString_writer()
    {
        return f_string_writer;
    }

    public void setString_writer(String p_string_writer)
    {
        f_string_writer = p_string_writer;
    }

    public String getString_plot()
    {
        return f_string_plot;
    }

    public void setString_plot(String p_string_plot)
    {
        f_string_plot = p_string_plot;
    }

    public String getString_country()
    {
        return f_string_country;
    }

    public void setString_country(String p_string_country)
    {
        f_string_country = p_string_country;
    }

    public Model_Movie()
    {
    }

    public String getString_title()
    {
        return f_string_title;
    }

    public void setString_title(String p_string_title)
    {
        f_string_title = p_string_title;
    }

    public String getString_year()
    {
        return f_string_year;
    }

    public void setString_year(String p_string_year)
    {
        f_string_year = p_string_year;
    }
}
