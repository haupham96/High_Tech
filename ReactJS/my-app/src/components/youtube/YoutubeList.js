import React from "react";
import { YoutubeData } from "../../data";
import YoutubeItem from "./YoutubeItem";

const YoutubeList = (props) => {
  console.log(props);
  return (
    <div className="youtube-list">
      <YoutubeItem
        image="https://images.unsplash.com/photo-1662948391946-ab57966b3b8f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"
        desc="I am Front End Developer"
        avt="https://images.unsplash.com/photo-1662933171679-61692083ae84?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388&q=80"
        author="HauPV"
      />
      {YoutubeData.map((item, index) => (
        <YoutubeItem
          key={item.id}
          image={item.image}
          title={item.title}
          author={item.author}
          avt={item.avatar}
          className={index === 1 ? "abc" : ""}
        />
      ))}
    </div>
  );
};

export default YoutubeList;
