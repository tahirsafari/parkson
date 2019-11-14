import React from 'react';
import ReactDOM from 'react-dom';

import FileBase64 from 'react-file-base64';

class ImageUpload extends React.Component {

  constructor() {
    super()
    this.state = {
      files: ''
    }
  }

  // Callback~
  getFiles(files){
    this.setState({ files: files })
    console.log("log "+JSON.stringify(this.state.files));
  }

  render() {
    return (
      <FileBase64
        multiple={ false }
        onDone={ this.getFiles.bind(this) } />
    )
  }

}
export default ImageUpload;