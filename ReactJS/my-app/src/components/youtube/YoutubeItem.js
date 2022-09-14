import React from "react";

const YoutubeItem = (props) => {
  return (
    <div className={`youtube-item ${props.className}`}>
      <div className="youtube-image">
        <img src={props.image} alt="" />
      </div>
      <div className="youtube-footer">
        <img src={props.avt} alt="" className="youtube-avt" />
        <div className="youtube-info">
          <h3 className="youtube-title">{props.title || "Default Title"}</h3>
          <p className="youtube-author">{props.author}</p>
        </div>
      </div>
    </div>
  );
};

export default YoutubeItem;
