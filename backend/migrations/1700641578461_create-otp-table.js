/* eslint-disable camelcase */

exports.shorthands = undefined;

exports.up = (pgm) => {
  pgm.createTable('otps', {
    users_email: {
      type: 'VARCHAR(50)',
      notNull: true,
      references: '"users"("email")',
      onDelete: 'cascade',
      onUpdate: 'cascade',
      unique: true,
    },
    otp: {
      type: 'char(6)',
      notNull: true,
    },
    expired_at: {
      type: 'timestamp',
      notNull: true,
      default: pgm.func('current_timestamp + interval \'5 minute\''),
    },
  });
};

exports.down = (pgm) => {
  pgm.dropTable('otps');
};
