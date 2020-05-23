import React from 'react';
import TextField from '@material-ui/core/TextField';

import global_data from '../../store/global_data';

interface Props {
  index: number;
}

function MenuInfoContent(props: Props) {
  const { index } = props;

  const onChange: any = (event: any) => {
    const input: string = event.target.value;

    global_data.menuInfos[index].content = input;
  };

  return (
    <TextField id={'menu-form-content-' + index} label="Content"
      defaultValue={global_data.menuInfos[index].content} margin="dense" onChange={onChange}/>
  );
}

export default MenuInfoContent;
